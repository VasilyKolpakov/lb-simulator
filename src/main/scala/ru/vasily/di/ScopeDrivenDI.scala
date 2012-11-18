package ru.vasily.di

import collection.immutable.ListMap

trait ScopeDrivenDI {
  protected def accept[T](injector: Injector[T]): (T, Map[String, Any]) = {
    val env = new Environment {
      def getValueWithConfig[A](key: String, clazz: Class[A]) = {
        val valueAndConfig = getInstance(key).getOrElse(throw new RuntimeException("no value for key = " + key + " in " + scopePath))
        valueAndConfig.asInstanceOf[(A, Any)]
      }
    }

    injector.create(env)
  }

  def getInstance(key: String): Option[(Any, Any)]

  protected[di] def scopePath: String
}

object EmptyScopeDrivenDI extends ScopeDrivenDI {
  def getInstance(key: String) = None

  protected[di] def scopePath = ""
}

class ScopeDrivenDIImpl private[di](scope: DIScope, name: String, parent: ScopeDrivenDI) extends ScopeDrivenDI {
  def getInstance(key: String): Option[(Any, Any)] = {
    val thisScopeInstanceOption =
      for (component <- scope.getComponent(key))
      yield instantiate(component, key)
    thisScopeInstanceOption
      .orElse(parent.getInstance(key))
  }

  // todo: add runtime type checking  (scala.reflect.Manifest?)
  def instantiate(component: SDComponent, key: String = ""): (Any, Any) = component match {
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
  private[di] def apply(initialScope: DIScope) = new ScopeDrivenDIImpl(initialScope, "", EmptyScopeDrivenDI)
}

class DIException(message: String, cause: Throwable = null)
  extends RuntimeException(message, cause)

