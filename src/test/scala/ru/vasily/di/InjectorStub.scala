package ru.vasily.di

case class InjectorStub(dependencies: Seq[String], typeName: String = "Stub") extends Injector[Map[String, Any]] {
  def create(env: Environment) = {
    val depList = dependencies.toList
    def create(depList: List[String], depInstancesList: List[Any] = Nil): (Map[String, Any], Map[String, Any]) = {
      val dep :: restDeps = depList
      val monad = env(dep, classOf[Any])
      if (restDeps.isEmpty) {
        monad.map {
          depInst =>
            val depInstances = depInst :: depInstancesList
            dependencies.zip(depInstances.reverse).toMap
        }
      } else {
        monad.flatMap(depInst => create(restDeps, depInst :: depInstancesList))
      }
    }

    create(depList)
  }

}

