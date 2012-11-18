package ru.vasily.di

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ScopeDrivenDITest extends FlatSpec with ShouldMatchers {

  "A ScopeDrivenDI" should "handle a primitive parameters case" in {
    val injector = InjectorStub(Seq("number", "string"))
    val scope = DIScope(
      "number" -> Primitive(1),
      "string" -> Primitive("str")
    )

    val expectedObject = Map("number" -> 1, "string" -> "str")
    ComplexComponent(injector, scope).instance should equal(expectedObject)
  }

  it should "handle single scope with primitive and complex objects" in {
    val topComponentInjector = InjectorStub(Seq("number", "complexComponent"))
    val complexComponentInjector = InjectorStub(Seq("number"))
    val scope = DIScope(
      "number" -> Primitive(1),
      "complexComponent" -> ComplexComponent(complexComponentInjector)
    )
    val expectedObject = Map(
      "number" -> 1,
      "complexComponent" -> Map("number" -> 1)
    )
    ComplexComponent(topComponentInjector, scope).instance should equal(expectedObject)
  }

  it should "support shadowing" in {
    val topComponentInjector = InjectorStub(Seq("number", "complexComponent"))
    val complexComponentInjector = InjectorStub(Seq("number"))
    val innerScope = DIScope("number" -> Primitive(2))
    val scope = DIScope(
      "number" -> Primitive(1),
      "complexComponent" -> ComplexComponent(complexComponentInjector, innerScope)
    )
    val expectedObject = Map(
      "number" -> 1,
      "complexComponent" -> Map("number" -> 2)
    )
    ComplexComponent(topComponentInjector, scope).instance should equal(expectedObject)
  }

  it should "handle complex case" in {
    val topComponentInjector = InjectorStub(Seq("number", "complexComponent1", "complexComponent2"))
    val complexComponent1Injector = InjectorStub(Seq("number"))
    val complexComponent2Injector = InjectorStub(Seq("complexComponent1", "number"))
    val innerScope = DIScope("number" -> Primitive(2))
    val scope = DIScope(
      "number" -> Primitive(1),
      "complexComponent1" -> ComplexComponent(complexComponent1Injector),
      "complexComponent2" -> ComplexComponent(complexComponent2Injector, innerScope)
    )
    val expectedObject = Map(
      "number" -> 1,
      "complexComponent1" -> Map("number" -> 1),
      "complexComponent2" -> Map(
        "number" -> 2,
        "complexComponent1" -> Map("number" -> 1)
      )
    )
    ComplexComponent(topComponentInjector, scope).instance should equal(expectedObject)
  }

  it should "create config" in {
    val topComponentInjector = InjectorStub(Seq("number", "complexComponent"), "Top")
    val complexComponentInjector = InjectorStub(Seq("number"), "ComplexComp")
    val scope = DIScope(
      "number" -> Primitive(1),
      "complexComponent" -> ComplexComponent(complexComponentInjector)
    )
    val expectedConfig = Map(
      "type" -> "Top",
      "number" -> 1,
      "complexComponent" -> Map("type" -> "ComplexComp", "number" -> 1)
    )
    ComplexComponent(topComponentInjector, scope).config should equal(expectedConfig)
  }

  it should "handle list components" in {
    val injector = InjectorStub(Seq("list"))
    val scope = DIScope(
      "list" -> SeqComponent(Seq(
        Primitive(1),
        Primitive(2)
      ))
    )
    val expectedInstance = Map(
      "list" -> Seq(1, 2)
    )
    val expectedConfig = Map(
      "type" -> "Stub",
      "list" -> Seq(1, 2)
    )
    ComplexComponent(injector, scope).instance should equal(expectedInstance)
    ComplexComponent(injector, scope).config should equal(expectedConfig)
  }

  it should "handle map components" in {
    val injector = InjectorStub(Seq("map"), "Comp")
    val innerInjector = InjectorStub(Seq(), "InnerComp")
    val scope = DIScope(
      "map" -> MapComponent(Map(
        "a" -> ComplexComponent(innerInjector)
      ))
    )
    val expectedInstance = Map(
      "map" -> Map("a" -> Map())
    )
    val expectedConfig = Map(
      "type" -> "Comp",
      "map" -> Map("a" -> Map("type" -> "InnerComp"))
    )
    ComplexComponent(injector, scope).instance should equal(expectedInstance)
    ComplexComponent(injector, scope).config should equal(expectedConfig)
  }

}
