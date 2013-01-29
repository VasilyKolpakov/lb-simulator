package ru.vasily.di

// TODO remove?
trait DIScope {
  def getComponent(key: String): Option[SDComponent]
}

case class MapDIScope(scope: Map[String, SDComponent]) extends DIScope {
  def getComponent(key: String): Option[SDComponent] = scope.get(key)
}

object DIScope {
  val empty = new DIScope {
    def getComponent(key: String) = None
  }

  def apply(scope: Map[String, SDComponent]): DIScope = MapDIScope(scope)

  def apply(components: (String, SDComponent)*): DIScope = DIScope(Map(components: _*))

}