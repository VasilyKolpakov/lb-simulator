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
    val rootInjector: InjectorStub = InjectorStub(Seq(), "root")
    val compInjector: InjectorStub = InjectorStub(Seq(), "comp")
    val injectors = Seq(rootInjector, compInjector)
    val diComponent = JsonDiLoader.createSDComponent(json, injectors, "root")


    val compScope = DIScope(
      "number" -> Primitive(BigDecimal(2))
    )
    val rootScope = DIScope(
      "number" -> Primitive(BigDecimal(1)),
      "complexComponent" -> ComplexComponent(compInjector, compScope)
    )
    val expectedDIComponent = ComplexComponent(rootInjector, rootScope)
    diComponent should equal(expectedDIComponent)
  }
  it should "allow fields without quotes in json" in {
    val json =
      """
        |{
        |  number : 1
        |}
      """.stripMargin
    val rootInjector: InjectorStub = InjectorStub(Seq(), "root")
    val diComponent = JsonDiLoader.createSDComponent(json, Seq(rootInjector), "root")
    val rootScope = DIScope(
      "number" -> Primitive(BigDecimal(1))
    )
    val expectedDIComponent = ComplexComponent(rootInjector, rootScope)
    diComponent should equal(expectedDIComponent)
  }

}
