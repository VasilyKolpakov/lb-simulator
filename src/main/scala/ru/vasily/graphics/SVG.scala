package ru.vasily.graphics

import xml.{Xhtml, Group, Node}

object SVG {
  def toSvg(shape: Shape[_], xTranslate: Int = 0, yTranslate: Int = 0): Node =
    shape.translate(xTranslate, yTranslate) match {
      case ComplexShape(shapes, x, y) => Group(
        shapes.map(toSvg(_, x, y))
      )
      case Rectangle(width, height, x, y, color) => {
        val Color(r, g, b) = color
          <rect x={x.toString}
                y={y.toString}
                width={width.toString}
                height={height.toString}
                style={"fill:rgb(%d,%d,%d)".format(r, g, b)}/>
      }
      case Line(x1, y1, x2, y2, color) => {
        val Color(r, g, b) = color
          <line x1={x1.toString}
                y1={y1.toString}
                x2={x2.toString}
                y2={y2.toString}
                style={"stroke:rgb(%s,%s,%s);stroke-width:2".format(r, g, b)}/>
      }
      case Text(text, x, y) =>
        <text x={x.toString} y={y.toString} font-size="20">
          {text}
        </text>
    }

  def toSvgString(shape: Shape[_]) = {
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
    val svg = Xhtml.toXhtml(toSvg(shape))
    Seq(header, svg, footer).mkString
  }
}
