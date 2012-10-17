package ru.vasily.di

trait DIScope {
  def getComponent(key: String): Option[SDComponent]

  def accept[T](injector: Injector[T]) = ScopeDrivenDI(this).accept(injector)
}

object DIScope {
  val empty = new DIScope {
    def getComponent(key: String) = None
  }

  def apply(scope: Map[String, SDComponent]): DIScope = new DIScope {
    def getComponent(key: String) = scope.get(key)
  }

  def apply(components: (String, SDComponent)*): DIScope = DIScope(Map(components: _*))

}