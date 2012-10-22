package ru.vasily.di

trait Injector[+T] {
  def create(env: Environment): (T, Map[String, Any])

  def typeName: String
}

object Injector {
  def apply[T](typeNameString: String)(f: (Environment) => (T, Map[String, Any])) =
    new Injector[T] {
      def create(env: Environment) = f(env)

      def typeName = typeNameString
    }
}
