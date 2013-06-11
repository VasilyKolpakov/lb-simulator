package ru.vasily.di

import collection.immutable.ListMap

trait ScopeDrivenDI {
  protected def accept[T](injector: Injector[T]): (T, Map[String, Any]) = {
    val env = new Environment {
      def getValueWithConfig[A](key: String, clazz: Class[A]) =
        getInstance(key, clazz).getOrElse {
          throw new RuntimeException("no value for key = " + key + " in " + scopePath)
        }
    }
    injector.create(env)
  }

  def getInstance[A](key: String, clazz: Class[A]): Option[(A, Any)]

  protected[di] def scopePath: String
}

object EmptyScopeDrivenDI extends ScopeDrivenDI {

  def getInstance[A](key: String, clazz: Class[A]): Option[(A, Any)] = None

  protected[di] def scopePath = ""
}

class ScopeDrivenDIImpl private[di](scope: DIScope, name: String, parent: ScopeDrivenDI) extends ScopeDrivenDI {
  def getInstance[A](key: String, clazz: Class[A]): Option[(A, Any)] = {
    scope.getComponent(key).map {
      case Primitive(value) => Primitive(castPrimitive(value, clazz))
      case x => x
    }.map(component => instantiate(component, key).asInstanceOf[(A, Any)])
      .orElse(parent.getInstance(key, clazz))
  }

  def castNumber[A](number: BigDecimal, clazz: Class[A]): A = {
    val DoubleClass = classOf[Double]
    val IntClass = classOf[Int]
    // without explicit type annotation ([AnyVal]) the scala compiler will implicitly convert Int to Double
    val castedNumber: AnyVal = clazz match {
      case IntClass => number.intValue()
      case DoubleClass => number.doubleValue()
    }
    castedNumber.asInstanceOf[A]
  }

  def castPrimitive[A](value: Any, clazz: Class[A]) = value match {
    case number: BigDecimal => castNumber(number, clazz)
    case x => x
  }

  // todo: add runtime type checking  (scala.reflect.Manifest?)
  def instantiate(component: SDComponent[Any], key: String): (Any, Any) = component match {
    case Primitive(value) => (value, value)
    case MapComponent(map) => {
      val instancesWithConfigs = map.map {
        case (mapKey, comp) => (mapKey, instantiate(comp, key + "/" + mapKey))
      }
      val mapConfig = instancesWithConfigs.mapValues(_._2)
      val mapInstance = instancesWithConfigs.mapValues(_._1)
      (mapInstance, mapConfig)
    }
    case SeqComponent(seq) => {
      val instancesWithConfig = seq.zipWithIndex.map {
        case (comp, i) => instantiate(comp, key + "[%d]".format(i))
      }
      instancesWithConfig.unzip
    }
    case ComplexComponent(injector, innerScope) => try {
      val (instance, config) = child(innerScope, key).accept(injector)
      (instance, ListMap("type" -> injector.typeName) ++ config)
    } catch {
      case e: DIException => throw e
      case e => throw new DIException("error during instatiation of " + injector.typeName + " in path " + scopePath, e)
    }
  }

  protected[di] def scopePath = parent.scopePath + name + "/"

  private def child(childScope: DIScope, name: String) = new ScopeDrivenDIImpl(childScope, name, this)

}

object ScopeDrivenDI {
  private[di] def apply(defaultScope: DIScope) = new ScopeDrivenDIImpl(defaultScope, "", EmptyScopeDrivenDI)

  def instantiateWithConfig[T](component: SDComponent[T], defaultScope: DIScope = DIScope.empty) =
    ScopeDrivenDI(defaultScope).instantiate(component, component.name).asInstanceOf[(T,Any)]

  def instantiate[T](component: SDComponent[T], defaultScope: DIScope = DIScope.empty) =
    instantiateWithConfig(component, defaultScope)._1

  def getConfig(component: SDComponent[_], defaultScope: DIScope = DIScope.empty) =
    instantiateWithConfig(component, defaultScope)._2
}

class DIException(message: String, cause: Throwable = null)
  extends RuntimeException(message, cause)

