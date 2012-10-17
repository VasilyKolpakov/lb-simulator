package ru.vasily.di

sealed trait SDComponent

case class ComplexObject[T](injector: Injector[T], scope: DIScope = DIScope.empty) extends SDComponent

case class Primitive(value: Any) extends SDComponent
