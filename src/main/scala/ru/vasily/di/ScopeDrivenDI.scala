package ru.vasily.di

import collection.immutable.ListMap

class ScopeDrivenDI private[di](scope: DIScope, name: String, parent: Option[ScopeDrivenDI] = None) {

  protected def accept[T](injector: Injector[T]): (T, Map[String, Any]) = {
    val env = new Environment {
      def getValueWithConfig[A](key: String, clazz: Class[A]) =
        getInstance(key, clazz).getOrElse {
          throw new RuntimeException("no value for key = " + key + " in " + scopePath)
        }
    }
    injector.create(env)
  }

  def getInstance[A](key: String, clazz: Class[A]): Option[(A, Any)] = {
    scope.getComponent(key).map {
      component =>
        val casted = castPrimitiveComponents(component, clazz)
        instantiate(casted, key).asInstanceOf[(A, Any)]
    } orElse parent.flatMap(_.getInstance(key, clazz))
  }

  private def castNumber[A](number: BigDecimal, clazz: Class[A]): A = {
    val DoubleClass = classOf[Double]
    val IntClass = classOf[Int]
    // without explicit type annotation ([AnyVal]) the scala compiler will implicitly convert Int to Double
    val castedNumber: AnyVal = clazz match {
      case IntClass => number.intValue()
      case DoubleClass => number.doubleValue()
    }
    castedNumber.asInstanceOf[A]
  }

  private def castPrimitiveComponents[A](comp: SDComponent[_], clazz: Class[A]) = comp match {
    case Primitive(value) => Primitive(castPrimitive(value, clazz))
    case x => x
  }

  private def castPrimitive[A](value: Any, clazz: Class[A]) = value match {
    case number: BigDecimal => castNumber(number, clazz)
    case x => x
  }

  // todo: add runtime type checking  (scala.reflect.Manifest?)
  def instantiate(component: SDComponent[Any], key: String): (Any, Any) = component match {
    case Primitive(value) => (value, value)

    case MapComponent(map) =>
      try {
        val instancesWithConfigs = map.map {
          case (mapKey, comp) => (mapKey, instantiate(comp, key + "/" + mapKey))
        }
        val mapConfig = instancesWithConfigs.mapValues(_._2)
        val mapInstance = instancesWithConfigs.mapValues(_._1)
        (mapInstance, mapConfig)
      }
      catch handleInstatiationErrors("Map", scopePath)

    case SeqComponent(seq) =>
      try {
        val instancesWithConfig = seq.zipWithIndex.map {
          case (comp, i) => instantiate(comp, s"$key[$i]")
        }
        instancesWithConfig.unzip
      } catch handleInstatiationErrors("Seq", scopePath)

    case ComplexComponent(injector, innerScope) =>
      try {
        val (instance, config) = child(innerScope, key).accept(injector)
        (instance, ListMap("type" -> injector.typeName) ++ config)
      } catch handleInstatiationErrors(injector.typeName, scopePath)
  }

  private def handleInstatiationErrors(componentName: String, scopePath: String): PartialFunction[Throwable, Nothing] = {
    case e: DIException => throw e
    case e: Exception => throw new DIException(s"error during instantiation of ${componentName} in path $scopePath", e)
  }

  protected[di] def scopePath: String = parent.map(_.scopePath).getOrElse("") + name + "/"

  private def child(childScope: DIScope, name: String) = new ScopeDrivenDI(childScope, name, Some(this))

}

object ScopeDrivenDI {
  private[di] def apply(defaultScope: DIScope) = new ScopeDrivenDI(defaultScope, "")

  def instantiateWithConfig[T](component: SDComponent[T], defaultScope: DIScope = DIScope.empty) =
    ScopeDrivenDI(defaultScope).instantiate(component, component.name).asInstanceOf[(T, Any)]

  def instantiate[T](component: SDComponent[T], defaultScope: DIScope = DIScope.empty) =
    instantiateWithConfig(component, defaultScope)._1

  def getConfig(component: SDComponent[_], defaultScope: DIScope = DIScope.empty) =
    instantiateWithConfig(component, defaultScope)._2
}



