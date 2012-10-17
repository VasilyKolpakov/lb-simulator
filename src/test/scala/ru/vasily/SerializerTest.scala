package ru.vasily

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class SerializerTest extends FlatSpec with ShouldMatchers {
  "A Serializer" should "serialize Map with numbers" in {
    val map = Map("1" -> 1)
    Serializer.marshal(map) should equal(
      """{
        |  "1" : 1
        |}""".stripMargin)
  }

  it should "serialize Map with case classes" in {
    case class Test(number: Int, a: String)

    val map = Map("1" -> Test(1, "a"))
    Serializer.marshal(map) should equal(
      """{
        |  "1" : {
        |    "number" : 1,
        |    "a" : "a"
        |  }
        |}""".stripMargin)
  }

  it should "serialize List with case classes" in {
    case class Test(n: Int)

    val map = List(Test(1), Test(2))
    Serializer.marshal(map) should equal(
      """[ {
        |  "n" : 1
        |}, {
        |  "n" : 2
        |} ]""".stripMargin)
  }

}
