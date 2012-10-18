package ru.vasily.di

sealed trait SDComponent {
  def instance = ScopeDrivenDI(DIScope.empty).instantiate(this)
}

case class ComplexComponent[T](injector: Injector[T], scope: DIScope = DIScope.empty) extends SDComponent

case class MapComponent(map: Map[String, SDComponent]) extends SDComponent

case class Primitive(value: Any) extends SDComponent
