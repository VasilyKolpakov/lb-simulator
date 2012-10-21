package ru.vasily.di

import java.lang.reflect.Constructor

class ClassInjector[T](val typeName: String, clazz: Class[T], dependenciesKeys: List[String]) extends Injector[T] {

  def create(env: Environment) = {
    val constructor = clazz.getDeclaredConstructors.apply(0)
      .asInstanceOf[Constructor[T]]
    val typesList = constructor.getParameterTypes.toList

    def newInstance(keyList: List[String], typeList: List[Class[_]], dependencies: List[Any] = Nil): (T, Map[String, Any]) = {
      val key :: restKeys = keyList
      val clazz :: restTypes = typeList
      val monad: ObjectMonad[Any] = env.apply(key, clazz.asInstanceOf[Class[Any]])
      if (restKeys.isEmpty) {
        monad.map(dep => {
          val constructorArgs = (dep :: dependencies).map(_.asInstanceOf[Object]).reverse
          constructor.newInstance(constructorArgs: _*)
        })
      } else {
        monad.flatMap(dep => newInstance(restKeys, restTypes, dep :: dependencies))
      }
    }

    newInstance(dependenciesKeys, typesList)
  }

}

object ClassInjector {
  def apply[T](typeName: String, clazz: Class[T], dependenciesKeys: String*) =
    new ClassInjector(typeName, clazz, dependenciesKeys.toList)
}