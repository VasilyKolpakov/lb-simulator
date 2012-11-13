package ru.vasily.graphics

import ru.vasily.graphics.SvgTrash.Color

sealed trait Shape[T <: Shape[T]] {
  this: T =>
  def translate(xShift: Int, yShift: Int): T
}

case class Rectangle(width: Int, height: Int, x: Int, y: Int, color: Color) extends Shape[Rectangle] {
  def translate(xShift: Int, yShift: Int) = copy(x = x + xShift, y = y + yShift)
}

case class Line(x1: Int, y1: Int, x2: Int, y2: Int, color: Color) extends Shape[Line] {
  def translate(xShift: Int, yShift: Int) =
    copy(
      x1 = x1 + xShift,
      x2 = x1 + xShift,
      y1 = y1 + yShift,
      y2 = y2 + yShift
    )
}

case class ComplexShape(shapes: Seq[Shape[_]], x: Int = 0, y: Int = 0) extends Shape[ComplexShape] {
  def translate(xShift: Int, yShift: Int) = copy(x = x + xShift, y = y + yShift)
}

case class Text(text: String, x: Int, y: Int) extends Shape[Text] {
  def translate(xShift: Int, yShift: Int) = copy(x = x + xShift, y = y + yShift)
}

case class Color(r: Int, g: Int, b: Int)

object Color {
  val black = Color(0, 0, 0)
  val green = Color(0, 100, 0)

  def grayShade(lightness: Int) = Color(lightness, lightness, lightness)
}

