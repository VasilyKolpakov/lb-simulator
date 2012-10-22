package ru.vasily.di

import collection.immutable.ListMap

// todo: refactor to something better
trait Environment {
  def apply[T](key: String, clazz: Class[T]): ObjectMonad[T] =
    new ObjectMonad[T](key, getValueWithConfig(key, clazz))

  def withConfig[T](key: String, clazz: Class[T]): ObjectWithConfigMonad[T] =
    new ObjectWithConfigMonad[T](key, getValueWithConfig(key, clazz))

  // todo: ?
  def seqWithConfig[T](key: String, clazz: Class[T]): SeqWithConfigMonad[T] = {
    new SeqWithConfigMonad[T](key, getValueWithConfig(key, clazz).asInstanceOf[(Seq[T], Seq[Any])])
  }

  def getValueWithConfig[T](key: String, clazz: Class[T]): (T, Any)

}

class ObjectMonad[T](key: String, obj: (T, Any)) {
  val monad = new ObjectWithConfigMonad[T](key, obj)

  def map[R](f: (T) => R) = monad.map {
    case (instance, config) => f(instance)
  }

  def flatMap[R](f: (T) => (R, Map[String, Any])) = monad.flatMap {
    case (instance, config) => f(instance)
  }
}

class ObjectWithConfigMonad[T](key: String, obj: (T, Any)) {
  def map[R](f: ((T, Any)) => R): (R, Map[String, Any]) = {
    val (_, objConfig) = obj
    (f(obj), ListMap(key -> objConfig))
  }

  def flatMap[R](f: ((T, Any)) => (R, Map[String, Any])): (R, Map[String, Any]) = {
    val (instance, objConfig) = f(obj)
    (instance, ListMap(key -> obj._2) ++ objConfig)
  }
}

class SeqWithConfigMonad[T](key: String, obj: (Seq[T], Seq[Any])) {
  def map[R](f: ((Seq[T], Seq[Any])) => R): (R, Map[String, Any]) = {
    val (_, objConfig) = obj
    (f(obj), ListMap(key -> objConfig))
  }

  def flatMap[R](f: ((Seq[T], Seq[Any])) => (R, Map[String, Any])): (R, Map[String, Any]) = {
    val (instance, objConfig) = f(obj)
    (instance, ListMap(key -> obj._2) ++ objConfig)
  }

}
