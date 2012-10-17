package ru.vasily.params

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import ru.vasily.di.{ScopeDrivenDIImpl, ScopeDrivenDI, InjectorStub}
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
    val injectors = Map("comp" -> InjectorStub("number"))
    val diScope = JsonDiLoader.createDIScope(json, injectors)

    val expectedObject = Map(
      "number" -> 1,
      "complexComponent" -> Map("number" -> 2)
    )
    diScope.accept(InjectorStub("number", "complexComponent")) should equal(expectedObject)
  }
  it should "allow fields without quotes in json" in {
    val json =
      """
        |{
        |  number : 1
        |}
      """.stripMargin
    val diScope = JsonDiLoader.createDIScope(json, Map.empty)

    val expectedObject = Map("number" -> 1)
    diScope.accept(InjectorStub("number")) should equal(expectedObject)
  }

}
