package ru.vasily.params

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import ru.vasily.di.{DIScope, ScopeDrivenDIImpl, ScopeDrivenDI, InjectorStub}
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
    val injectors = Map(
      "root" -> InjectorStub("number", "complexComponent"),
      "comp" -> InjectorStub("number")
    )
    val diComponent = JsonDiLoader.createSDComponent(json, injectors, "root")

    val expectedObject = Map(
      "number" -> 1,
      "complexComponent" -> Map("number" -> 2)
    )
    diComponent.instance should equal(expectedObject)
  }
  it should "allow fields without quotes in json" in {
    val json =
      """
        |{
        |  number : 1
        |}
      """.stripMargin
    val diComponent = JsonDiLoader.createSDComponent(json, Map("root" -> InjectorStub("number")), "root")

    val expectedObject = Map("number" -> 1)
    diComponent.instance should equal(expectedObject)
  }

}
