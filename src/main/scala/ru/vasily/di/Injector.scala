package ru.vasily.di

trait Injector[+T] {
// TODO def componentName: String

  def create(env: Environment): T
}
