package ru.vasily.di

// todo: refactor to something better
trait Environment {
  def apply[T](key: String, clazz: Class[T]): ObjectMonad[T] =
    new ObjectMonad[T](key, getValueWithConfig(key, clazz))

  def withConfig[T](key: String, clazz: Class[T]): ObjectWithConfigMonad[T] =
    new ObjectWithConfigMonad[T](key, getValueWithConfig(key, clazz))

  // todo: ?
  def seqWithConfig[T<:Seq[_]](key: String, clazz: Class[T]): SeqWithConfigMonad[T] =
    new SeqWithConfigMonad[T](key, getValueWithConfig(key, clazz).asInstanceOf[(T, Seq[Any])])

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
    (f(obj), Map(key -> objConfig))
  }

  def flatMap[R](f: ((T, Any)) => (R, Map[String, Any])): (R, Map[String, Any]) = {
    val (instance, objConfig) = f(obj)
    (instance, objConfig.updated(key, obj._2))
  }
}

class SeqWithConfigMonad[T <: Seq[_]](key: String, obj: (T, Seq[Any])) {
  def map[R](f: ((T, Seq[Any])) => R): (R, Map[String, Any]) = {
    val (_, objConfig) = obj
    (f(obj), Map(key -> objConfig))
  }

  def flatMap[R](f: ((T, Seq[Any])) => (R, Map[String, Any])): (R, Map[String, Any]) = {
    val (instance, objConfig) = f(obj)
    (instance, objConfig.updated(key, obj._2))
  }

}
