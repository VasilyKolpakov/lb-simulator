package ru.vasily.di

trait Environment {
  def get(key: String): Option[Any]

  def apply[T](key: String): T
}

