package ru.vasily.di

import java.lang.reflect.Constructor

class ClassInjector[T](val typeName: String, clazz: Class[T], dependenciesKeys: List[String]) extends Injector[T] {

  def create(env: Environment) = {
    val constructor = clazz.getDeclaredConstructors.apply(0)
      .asInstanceOf[Constructor[T]]
    val typesList = constructor.getParameterTypes.toList
    if (dependenciesKeys.size != typesList.size) {
      throw new ClassInjectorException(
        "wrong nuber of dependencies: %d not %d".format(dependenciesKeys.size, typesList.size)
      )
    }

    def newInstance(keyClassList: List[(String, Class[_])], dependencies: List[Any] = Nil): (T, Map[String, Any]) = {
      val (key, clazz) :: rest = keyClassList
      val monad = env.apply(key, clazz.asInstanceOf[Class[Any]])
      if (rest.isEmpty) {
        monad.map(dep => {
          val constructorArgs = (dep :: dependencies).map(_.asInstanceOf[Object]).reverse
          constructor.newInstance(constructorArgs: _*)
        })
      } else {
        monad.flatMap(dep => newInstance(rest, dep :: dependencies))
      }
    }
    try {
      val keyClassList = dependenciesKeys.zip(typesList)
      if (typesList.isEmpty) {
        (constructor.newInstance(), Map())
      } else {
        newInstance(keyClassList)
      }
    } catch {
      case e: ClassInjectorException => throw e
      case x => throw new ClassInjectorException("error during instantiating " + typeName, x)
    }
  }

  class ClassInjectorException(message: String = "", cause: Throwable = null)
    extends RuntimeException("error during instantiating " + typeName + ": " + message, cause)

}

object ClassInjector {
  def apply[T](typeName: String, clazz: Class[T], dependenciesKeys: String*) =
    new ClassInjector(typeName, clazz, dependenciesKeys.toList)
}

