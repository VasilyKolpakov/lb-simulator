package ru.vasily.di

class InjectorStub(dependencies: Seq[String]) extends Injector[Map[String, Any]] {
  def create(env: Environment) = {
    dependencies.map {
      dependencyKey => (dependencyKey, env(dependencyKey))
    }.toMap
  }

}

object InjectorStub {
  def apply(dependencies: String*) = new InjectorStub(dependencies)
}

