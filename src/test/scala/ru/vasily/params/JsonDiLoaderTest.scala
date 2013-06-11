package ru.vasily.params

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import ru.vasily.di._
import java.io.StringReader

class JsonDiLoaderTest extends FlatSpec with ShouldMatchers {
  "A JsonDiLoader" should "load di" in {
    val json =
      """
        |{
        |"number" : 1,
        |"complexComponent" :
        |  {
        |    "type": "comp",
        |    "number" : 2
        |  }
        |}
      """.stripMargin
    val compInjector: InjectorStub = InjectorStub(Seq(), "comp")
    val injectors = Seq(compInjector)
    val diScope = JsonDiLoader.createDIScope(json, injectors)

    val compScope = DIScope(
      "number" -> Primitive(BigDecimal(2))
    )
    val expectedScope = DIScope(
      "number" -> Primitive(BigDecimal(1)),
      "complexComponent" -> ComplexComponent(compInjector, compScope)
    )
    diScope should equal(expectedScope)
  }
  it should "allow fields without quotes in json" in {
    val json =
      """
        |{
        |  number : 1
        |}
      """.stripMargin
    val diScope = JsonDiLoader.createDIScope(json, Seq())
    val expectedScope = DIScope(
      "number" -> Primitive(BigDecimal(1))
    )
    diScope should equal(expectedScope)
  }

}
