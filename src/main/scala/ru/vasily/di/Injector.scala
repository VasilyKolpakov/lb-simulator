package ru.vasily.di

trait Injector[+T] {
  def create(env: Environment): (T, Map[String, Any])

  def typeName: String
}
