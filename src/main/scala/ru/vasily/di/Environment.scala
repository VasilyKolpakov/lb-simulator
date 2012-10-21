package ru.vasily.di

trait Environment {
  def apply[T](key: String, clazz: Class[T]): ObjectMonad[T] =
    new ObjectMonad[T](key, getValueWithConfig(key, clazz))

  def withConfig[T](key: String, clazz: Class[T]): MapObjectMonad[T] =
    new MapObjectMonad[T](key, getValueWithConfig(key, clazz))

  def getValueWithConfig[T](key: String, clazz: Class[T]): (T, Any)

}

class ObjectMonad[T](key: String, obj: (T, Any)) {
  val monad = new MapObjectMonad[T](key, obj)

  def map[R](f: (T) => R) = monad.map {
    case (instance, config) => f(instance)
  }

  def flatMap[R](f: (T) => (R, Map[String, Any])) = monad.flatMap {
    case (instance, config) => f(instance)
  }
}

class MapObjectMonad[T](key: String, obj: (T, Any)) {
  def map[R](f: ((T, Any)) => R): (R, Map[String, Any]) = {
    val (_, objConfig) = obj
    (f(obj), Map(key -> objConfig))
  }

  def flatMap[R](f: ((T, Any)) => (R, Map[String, Any])): (R, Map[String, Any]) = {
    val (instance, objConfig) = f(obj)
    (instance, objConfig.updated(key, obj._2))
  }
}
