package ru.vasily.di

import collection.immutable.ListMap

// todo: refactor to something better
trait Environment {
  def apply[T](key: String, clazz: Class[T]): ObjectForComprehension[T] =
    new ObjectForComprehension[T](key, getValueWithConfig(key, clazz))

  def withConfig[T](key: String, clazz: Class[T]): ObjectWithConfigForComprehension[T] =
    new ObjectWithConfigForComprehension[T](key, getValueWithConfig(key, clazz))

  // todo: ?
  def seqWithConfig[T](key: String, clazz: Class[T]): SeqWithConfigForComprehension[T] = {
    new SeqWithConfigForComprehension[T](key, getValueWithConfig(key, clazz).asInstanceOf[(Seq[T], Seq[Any])])
  }

  def getValueWithConfig[T](key: String, clazz: Class[T]): (T, Any)

}

class ObjectForComprehension[T](key: String, obj: (T, Any)) {
  val forComprehension = new ObjectWithConfigForComprehension[T](key, obj)

  def map[R](f: (T) => R) = forComprehension.map {
    case (instance, config) => f(instance)
  }

  def flatMap[R](f: (T) => (R, Map[String, Any])) = forComprehension.flatMap {
    case (instance, config) => f(instance)
  }
}

class ObjectWithConfigForComprehension[T](key: String, obj: (T, Any)) {
  def map[R](f: ((T, Any)) => R): (R, Map[String, Any]) = {
    val (_, objConfig) = obj
    (f(obj), ListMap(key -> objConfig))
  }

  def flatMap[R](f: ((T, Any)) => (R, Map[String, Any])): (R, Map[String, Any]) = {
    val (instance, objConfig) = f(obj)
    (instance, ListMap(key -> obj._2) ++ objConfig)
  }
}

class SeqWithConfigForComprehension[T](key: String, obj: (Seq[T], Seq[Any])) {
  def map[R](f: ((Seq[T], Seq[Any])) => R): (R, Map[String, Any]) = {
    val (_, objConfig) = obj
    (f(obj), ListMap(key -> objConfig))
  }

  def flatMap[R](f: ((Seq[T], Seq[Any])) => (R, Map[String, Any])): (R, Map[String, Any]) = {
    val (instance, objConfig) = f(obj)
    (instance, ListMap(key -> obj._2) ++ objConfig)
  }

}
