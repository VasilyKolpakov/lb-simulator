package ru.vasily.di

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ScopeDrivenDITest extends FlatSpec with ShouldMatchers {

  "A ScopeDrivenDI" should "handle a primitive parameters case" in {
    val objectFactory = InjectorStub("number", "string")
    val scope = DIScope(
      "number" -> Primitive(1),
      "string" -> Primitive("str")
    )

    val expectedObject = Map("number" -> 1, "string" -> "str")
    ScopeDrivenDI(scope).accept(objectFactory) should equal(expectedObject)
  }

  it should "handle single scope with primitive and complex objects" in {
    val topComponentFactory = InjectorStub("number", "complexComponent")
    val complexComponentFactory = InjectorStub("number")
    val scope = DIScope(
      "number" -> Primitive(1),
      "complexComponent" -> ComplexComponent(complexComponentFactory)
    )
    val expectedObject = Map(
      "number" -> 1,
      "complexComponent" -> Map("number" -> 1)
    )
    ScopeDrivenDI(scope).accept(topComponentFactory) should equal(expectedObject)
  }

  it should "support shadowing" in {
    val topComponentFactory = InjectorStub("number", "complexComponent")
    val complexComponentFactory = InjectorStub("number")
    val innerScope = DIScope("number" -> Primitive(2))
    val scope = DIScope(
      "number" -> Primitive(1),
      "complexComponent" -> ComplexComponent(complexComponentFactory, innerScope)
    )
    val expectedObject = Map(
      "number" -> 1,
      "complexComponent" -> Map("number" -> 2)
    )
    ScopeDrivenDI(scope).accept(topComponentFactory) should equal(expectedObject)
  }

  it should "handle complex case" in {
    val topComponentFactory = InjectorStub("number", "complexComponent1", "complexComponent2")
    val complexComponent1Factory = InjectorStub("number")
    val complexComponent2Factory = InjectorStub("complexComponent1", "number")
    val innerScope = DIScope("number" -> Primitive(2))
    val scope = DIScope(
      "number" -> Primitive(1),
      "complexComponent1" -> ComplexComponent(complexComponent1Factory),
      "complexComponent2" -> ComplexComponent(complexComponent2Factory, innerScope)
    )
    val expectedObject = Map(
      "number" -> 1,
      "complexComponent1" -> Map("number" -> 1),
      "complexComponent2" -> Map(
        "number" -> 2,
        "complexComponent1" -> Map("number" -> 1)
      )
    )
    ScopeDrivenDI(scope).accept(topComponentFactory) should equal(expectedObject)
  }
}
