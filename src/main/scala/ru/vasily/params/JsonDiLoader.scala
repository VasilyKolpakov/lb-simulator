package ru.vasily.params

import ru.vasily.di._
import ru.vasily.di.Primitive
import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.fasterxml.jackson.core.JsonParser

object JsonDiLoader {
  private val TYPE_RESERVED_WORD: String = "type"
  private val mapper = new ObjectMapper()
  mapper.getFactory.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
  mapper.getFactory.enable(JsonParser.Feature.ALLOW_COMMENTS)

  def createDIScope(json: String, injectors: Seq[Injector[_]]): DIScope = {
    val injectorsMap = injectors.map(inj => (inj.typeName, inj)).toMap
    def toSDComponent(parsedJson: Any): SDComponent[Any] = parsedJson match {
      case number: BigDecimal => Primitive(number)
      case str: String => Primitive(str)
      case map: Map[String, Any] => {
        val typeKeyOption = map.get(TYPE_RESERVED_WORD)
          .asInstanceOf[Option[String]]
        typeKeyOption match {
          case Some(typeKey) => toComplexComponent(map, typeKey)
          case None => toMapComponent(map)
        }
      }
      case seq: Seq[Any] => SeqComponent(seq.map(obj => toSDComponent(obj)))
    }

    def toComplexComponent(map: Map[String, Any], typeKey: String) = {
      val injector = injectorsMap.get(typeKey)
        .getOrElse(throw new RuntimeException( s""""unsupported type "${typeKey}" """))
      ComplexComponent(injector, toDIScope(map - TYPE_RESERVED_WORD))
    }

    def toMapComponent(map: Map[String, Any]) = {
      val components = map.map {
        case (mapKey, obj) => (mapKey, toSDComponent(obj))
      }
      MapComponent(components)
    }

    def toDIScope(parsedJson: Any): DIScope = {
      val scope = parsedJson.asInstanceOf[Map[String, Any]].map {
        case (key, obj) => (key, toSDComponent(obj))
      }
      DIScope.fromMap(scope)
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
    } else if (json.isArray) {
      val nodes = json.asScala
      nodes.map(toScalaCollections(_))
    } else {
      val keyNodePairs = for (field <- json.fields().asScala)
      yield (field.getKey, toScalaCollections(field.getValue))
      keyNodePairs.toMap
    }
  }

}
