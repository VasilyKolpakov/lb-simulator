package ru.vasily.di

class InjectorStub(private val dependencies: Seq[(String, Class[_])], val typeName: String) extends Injector[Map[String, Any]] {
  def create(env: Environment) = {
    val (instances, configs) =
      dependencies.map {
        case (key, clazz) => env.getValueWithConfig(key, clazz)
      }.unzip

    val dependencyKeys = dependencies.map(_._1)
    def createMap[K, V](keys: Seq[K], values: Seq[V]) = keys.zip(values).toMap
    (createMap(dependencyKeys, instances), createMap(dependencyKeys, configs))
  }

  override def equals(obj: Any): Boolean =
    Option(obj).collect {
      case stub: InjectorStub => stub.dependencies == dependencies && stub.typeName == typeName
    }.getOrElse(false)
}

object InjectorStub {
  def apply(dependencies: Seq[String], typeName: String = "Stub"): InjectorStub =
    new InjectorStub(dependencies.map((_, classOf[Any])), typeName)

  def withTypeChecking(dependencies: Seq[(String, Class[_])], typeName: String = "Stub") =
    new InjectorStub(dependencies, typeName)
}
