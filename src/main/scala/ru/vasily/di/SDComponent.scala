package ru.vasily.di

sealed trait SDComponent {

  // todo ? move methods to ScopeDrivenDI
  def instance = ScopeDrivenDI(DIScope.empty).instantiate(this)._1

  def config = ScopeDrivenDI(DIScope.empty).instantiate(this)._2
}

case class ComplexComponent[T](injector: Injector[T], scope: DIScope = DIScope.empty) extends SDComponent

case class MapComponent(map: Map[String, SDComponent]) extends SDComponent

case class SeqComponent(seq: Seq[SDComponent]) extends SDComponent

case class Primitive(value: Any) extends SDComponent
