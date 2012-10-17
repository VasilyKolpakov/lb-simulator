package ru.vasily

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import java.io.StringWriter

object Serializer {
  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  def marshal(value: AnyRef) = {
    val writer = new StringWriter()
    mapper.writerWithDefaultPrettyPrinter().writeValue(writer, value)
    writer.getBuffer.toString
  }
}
