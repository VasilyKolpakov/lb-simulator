package ru.vasily.di

import java.lang.reflect.Constructor

class ClassInjector[T](val typeName: String, clazz: Class[T], dependenciesKeys: List[String]) extends Injector[T] {

  def create(env: Environment) = {
    val constructor = clazz.getDeclaredConstructors.apply(0)
      .asInstanceOf[Constructor[T]]
    val typesList = constructor.getParameterTypes.toList
    if (dependenciesKeys.size != typesList.size) {
      throw new RuntimeException(s"cannot instantiate $typeName: " +
        s"wrong number of dependencies: ${dependenciesKeys.size} not ${typesList.size}")
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
    val keyClassList = dependenciesKeys.zip(typesList)
    if (typesList.isEmpty) {
      (constructor.newInstance(), Map())
    } else {
      newInstance(keyClassList)
    }
  }
}

object ClassInjector {
  def apply[T](typeName: String, clazz: Class[T], dependenciesKeys: String*) =
    new ClassInjector(typeName, clazz, dependenciesKeys.toList)
}

