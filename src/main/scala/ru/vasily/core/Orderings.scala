package ru.vasily.core

object Orderings {
  def reversedOrdering[T](implicit ord: Ordering[T]) = new Ordering[T] {
    def compare(a: T, b: T) = ord.compare(b, a)
  }
}
