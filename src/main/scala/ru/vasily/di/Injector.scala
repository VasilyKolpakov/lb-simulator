package ru.vasily.di

trait Injector[+T] {
  def create(env: Environment): (T, Map[String, Any])

  def typeName: String

  override def toString = "Injector(" + typeName + ")"
}

object Injector {
  def apply[T](typeNameString: String)(f: (Environment) => (T, Map[String, Any])) =
    new Injector[T] {
      def create(env: Environment) = f(env)

      def typeName = typeNameString
    }

  def apply[T](typeNameString: String, obj: T) =
    new Injector[T] {
      def create(env: Environment) = (obj, Map())

      def typeName = typeNameString
    }

}
