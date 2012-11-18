package ru.vasily.di

case class InjectorStub(dependencies: Seq[String], typeName: String = "Stub") extends Injector[Map[String, Any]] {
  def create(env: Environment) = {
    val (instances, configs) = dependencies.map(env.getValueWithConfig(_, classOf[Any])).unzip
    def createMap[T](seq: Seq[T]) = dependencies.zip(seq).toMap
    (createMap(instances), createMap(configs))
  }

}

