package ru.vasily.di

trait ScopeDrivenDI {
  def accept[T](injector: Injector[T]): T = {
    val env = new Environment {
      def apply[A](key: String) = {
        val value = get(key).getOrElse(throw new RuntimeException("no value for key = " + key + " in " + scopePath))
        value.asInstanceOf[A]
      }

      def get(key: String) = getInstance(key)
    }

    injector.create(env)
  }

  def getInstance(key: String): Option[Any]

  protected[di] def scopePath: String
}

object EmptyScopeDrivenDI extends ScopeDrivenDI {
  def getInstance(key: String) = None

  protected[di] def scopePath = ""
}

class ScopeDrivenDIImpl private[di](scope: DIScope, name: String, parent: ScopeDrivenDI) extends ScopeDrivenDI {
  def getInstance(key: String): Option[Any] = {
    val thisScopeInstanceOption =
      for (component <- scope.getComponent(key))
      yield component match {
        case Primitive(value) => value
        case ComplexObject(injector, innerScope) => child(innerScope, key).accept(injector)
      }
    thisScopeInstanceOption
      .orElse(parent.getInstance(key))
  }


  protected[di] def scopePath = parent.scopePath + name + "/"

  private def child(childScope: DIScope, name: String) = new ScopeDrivenDIImpl(childScope, name, this)

}

object ScopeDrivenDI {
  def apply(initialScope: DIScope) = new ScopeDrivenDIImpl(initialScope, "", EmptyScopeDrivenDI)
}