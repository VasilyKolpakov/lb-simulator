package ru.vasily.graphics

import xml._
import xml.Group
import ru.vasily.RichFile._
import java.io.File

object SvgTrash {

  case class Color(r: Int, g: Int, b: Int)

  object Color {
    val black = Color(0, 0, 0)
    val green = Color(0, 100, 0)
  }

  trait Shape

  case class Rectangle(wight: Int, height: Int, x: Int, y: Int, color: Color) extends Shape

  case class Line(x1: Int, y1: Int, x2: Int, y2: Int, color: Color) extends Shape

  case class ComplexShape(shapes: Seq[Shape], x: Int = 0, y: Int = 0) extends Shape {
    def translate(xShift: Int, yShift: Int) = ComplexShape(shapes, x + xShift, y + yShift)
  }

  def toSvg(shape: Shape, xTranslate: Int = 0, yTranslate: Int = 0): Node = shape match {
    case Rectangle(wight, height, x, y, color) => {
      val Color(r, g, b) = color
        <rect x={(x + xTranslate).toString}
              y={(y + yTranslate).toString}
              width={wight.toString}
              height={height.toString}
              style={"fill:rgb(%d,%d,%d)".format(r, g, b)}/>
    }
    case Line(x1, y1, x2, y2, color) => {
      val Color(r, g, b) = color
        <line x1={(x1 + xTranslate).toString}
              y1={(y1 + yTranslate).toString}
              x2={(x2 + xTranslate).toString}
              y2={(y2 + yTranslate).toString}
              style={"stroke:rgb(%s,%s,%s);stroke-width:2".format(r, g, b)}/>
    }
    case ComplexShape(shapes, x, y) => Group(
      shapes.map(toSvg(_, xTranslate + x, yTranslate + y))
    )
  }

  def main(args: Array[String]) {
    def task(serverIndex: Int, executionTime: Int, completionTime: Int) = {
      val rect = Rectangle(executionTime, 30, 0, 0, Color.green)
      def borderLine(x: Int) = Line(x, -10, x, 40, Color.black)
      ComplexShape(Seq(rect, borderLine(0), borderLine(executionTime)),
        completionTime - executionTime, serverIndex * 60)
    }
    val tasks = ComplexShape(Seq(
      task(0, 100, 200),
      task(2, 250, 500),
      task(1, 350, 500),
      task(2, 20, 200)
    ))

    val header =
      """<?xml version="1.0" encoding="UTF-8" standalone="no"?>
        |<!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 1.1//EN"  "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd">
        |<svg version="1.1"
        |     baseProfile="full"
        |     xmlns="http://www.w3.org/2000/svg"
        |     xmlns:xlink="http://www.w3.org/1999/xlink"
        |     xmlns:ev="http://www.w3.org/2001/xml-events"
        |     width="100%" height="100%">
      """.stripMargin
    val footer = "\n</svg>"
    val svg = Xhtml.toXhtml(toSvg(tasks))
    new File("/home/vasily/Development/trash/scala_svg/svg.html").text = Seq(header, svg, footer).mkString
  }
}
