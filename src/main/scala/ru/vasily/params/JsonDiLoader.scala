package ru.vasily.params

import ru.vasily.di._
import ru.vasily.di.Primitive
import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.fasterxml.jackson.core.JsonParser

object JsonDiLoader {
  private val TYPE_RESERVED_WORD: String = "type"
  private val mapper = new ObjectMapper()
  mapper.getFactory.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)

  def createDIScope(json: String, injectors: Map[String, Injector[Any]]): DIScope = {
    def toSDComponent(parsedJson: Any, key: String) = parsedJson match {
      case number: BigDecimal => {
        Primitive(number.intValue())
      }
      case str: String => Primitive(str)
      case map: Map[String, Any] => {
        val typeKey = map.get(TYPE_RESERVED_WORD)
          .getOrElse(throw new RuntimeException("type is not specified for " + key))
          .asInstanceOf[String]
        val injector = injectors.get(typeKey)
          .getOrElse(throw new RuntimeException("unsupported type " + typeKey))
        ComplexObject(injector, toDIScope(map - TYPE_RESERVED_WORD))
      }
    }

    def toDIScope(parsedJson: Any): DIScope = {
      val scope = parsedJson.asInstanceOf[Map[String, Any]].map {
        case (key, obj) => (key, toSDComponent(obj, key))
      }
      DIScope(scope)
    }

    val parsedJson: Any = toScalaCollections(mapper.readTree(json))
    toDIScope(parsedJson)
  }


  private def toScalaCollections(json: JsonNode): Any = {
    import scala.collection.JavaConverters._
    if (json.isNumber) {
      BigDecimal(json.asText())
    } else if (json.isTextual) {
      json.textValue()
    } else {
      val keyNodePairs = for (field <- json.fields().asScala)
      yield (field.getKey, toScalaCollections(field.getValue))
      keyNodePairs.toMap
    }
  }

}
