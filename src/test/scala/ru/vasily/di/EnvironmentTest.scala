package ru.vasily.di

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class EnvironmentTest extends FlatSpec with ShouldMatchers {

  case class Obj(n: Int)

  val envMap = Map(
    "number" ->(1, 1),
    "str" ->("string", "string"),
    "obj" ->(Obj(1), Map("n" -> 1))
  )

  val env: Environment = new Environment {
    def getValueWithConfig[T](key: String, clazz: Class[T]) = envMap(key).asInstanceOf[(T, Any)]
  }

  "An Environment" should "support monadic operations" in {
    val (list, config) = for {
      number <- env("number", classOf[Int])
      str <- env("str", classOf[String])
      obj <- env("obj", classOf[Obj])
    } yield List(number, str, obj)

    list should equal(List(1, "string", Obj(1)))

    val expectedConfig = Map(
      "number" -> 1,
      "str" -> "string",
      "obj" -> Map("n" -> 1)
    )
    config should equal(expectedConfig)
  }

  it should "pass config" in {
    val (list, config) = for {
      number <- env.withConfig("number", classOf[Int])
      str <- env.withConfig("str", classOf[String])
      obj <- env.withConfig("obj", classOf[Obj])
    } yield List(number, str, obj)

    list should equal(List(
      (1, 1),
      ("string", "string"),
      (Obj(1), Map("n" -> 1))
    ))

    val expectedConfig = Map(
      "number" -> 1,
      "str" -> "string",
      "obj" -> Map("n" -> 1)
    )
    config should equal(expectedConfig)
  }
}
