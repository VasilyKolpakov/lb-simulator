package ru.vasily.di

sealed trait SDComponent[+T] {
  def name = this match {
    case c: ComplexComponent[_] => c.injector.typeName
    case m: MapComponent[_] => "Map"
    case s: SeqComponent[_] => "Seq"
    case p: Primitive[_] => "Primitive"
  }

  def instance = ScopeDrivenDI.instantiate(this)

  def config = ScopeDrivenDI.getConfig(this)
}

case class ComplexComponent[T](injector: Injector[T], scope: DIScope = DIScope.empty) extends SDComponent[T]

case class MapComponent[T](map: Map[String, SDComponent[T]]) extends SDComponent[Map[String, T]]

case class SeqComponent[T](seq: Seq[SDComponent[T]]) extends SDComponent[Seq[T]]

case class Primitive[T](value: T) extends SDComponent[T]
