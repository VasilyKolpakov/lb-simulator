package ru.vasily.di

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ClassInjectorTest extends FlatSpec with ShouldMatchers {
  "A ClassInjector" should "handle case classes" in {
    val env = new Environment {
      def getValueWithConfig[T](key: String, clazz: Class[T]) = ((1, 1)).asInstanceOf[(T, Any)]
    }

    val injector = new ClassInjector("test", classOf[TestCaseClass], List("i"))
    injector.create(env)._1 should equal(TestCaseClass(1))
  }

  it should "handle classes" in {
    val env = new Environment {
      def getValueWithConfig[T](key: String, clazz: Class[T]) = ((1, 1)).asInstanceOf[(T, Any)]
    }
    val injector = new ClassInjector("test", classOf[TestClass], List("i"))

    injector.create(env)._1.i should equal(1)
  }

  it should "handle more than one argument constructors" in {
    val envMap = Map(
      "n" ->(1, 1),
      "obj" ->(TestCaseClass(1), Map("i" -> 1))
    )
    val env = new Environment {
      def getValueWithConfig[T](key: String, clazz: Class[T]) = envMap(key).asInstanceOf[(T, Any)]
    }
    val injector = new ClassInjector("test", classOf[TestClassWith2ArgConstructor], List("n", "obj"))

    injector.create(env)._1.n should equal(1)
  }

}

case class TestCaseClass(i: Int)

class TestClass(val i: Int)

case class TestClassWith2ArgConstructor(n: Int, obj: TestCaseClass)

