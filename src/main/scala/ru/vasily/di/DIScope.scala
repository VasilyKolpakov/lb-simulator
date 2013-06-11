package ru.vasily.di

// TODO remove?
trait DIScope {
  def getComponent(key: String): Option[SDComponent[Any]]
}

case class MapDIScope(scope: Map[String, SDComponent[Any]]) extends DIScope {
  def getComponent(key: String): Option[SDComponent[Any]] = scope.get(key)
}

object DIScope {
  val empty = new DIScope {
    def getComponent(key: String) = None
  }

  def fromMap(scope: Map[String, SDComponent[Any]]): DIScope = MapDIScope(scope)

  def apply(components: (String, SDComponent[Any])*): DIScope = DIScope.fromMap(Map(components: _*))

}