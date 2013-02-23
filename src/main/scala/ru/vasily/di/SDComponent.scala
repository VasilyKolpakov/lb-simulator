package ru.vasily.di

sealed trait SDComponent {

  // todo ? move methods to ScopeDrivenDI
  def instance = ScopeDrivenDI(DIScope.empty).instantiate(this, SDComponent.componentName(this))._1

  def config = ScopeDrivenDI(DIScope.empty).instantiate(this, SDComponent.componentName(this))._2
}

object SDComponent {
  private val componentName: (SDComponent => String) = {
    case c: ComplexComponent[_] => c.injector.typeName
    case m: MapComponent => "Map"
    case s: SeqComponent => "Seq"
    case p: Primitive => "Primitive"
  }
}

case class ComplexComponent[T](injector: Injector[T], scope: DIScope = DIScope.empty) extends SDComponent

case class MapComponent(map: Map[String, SDComponent]) extends SDComponent

case class SeqComponent(seq: Seq[SDComponent]) extends SDComponent

case class Primitive(value: Any) extends SDComponent
