package ru.vasily.core

import collection.immutable.{Queue, SortedMap}
import Orderings.reversedOrdering

class PriorityQueue[A] private(private val sortedMap: SortedMap[A, Queue[A]]) {

  def enqueue(elem: A): PriorityQueue[A] = {
    val queue = sortedMap.get(elem)
      .map(_.enqueue(elem))
      .getOrElse(Queue(elem))
    new PriorityQueue(sortedMap.updated(elem, queue))
  }

  def head = {
    val (_, queue) = sortedMap.head
    queue.head
  }

  def dequeueOption: Option[(A, PriorityQueue[A])] = if (isEmpty) {
    None
  } else {
    val (key, queue) = sortedMap.head
    val newQueue = queue.tail
    val newMap = if (newQueue.isEmpty) {
      sortedMap - key
    } else {
      sortedMap.updated(key, newQueue)
    }
    Some((queue.head, new PriorityQueue(newMap)))
  }

  def ++(elems: Seq[A]): PriorityQueue[A] =
    elems.foldLeft(this) {
      (queue: PriorityQueue[A], elem: A) => queue.enqueue(elem)
    }


  def isEmpty: Boolean = sortedMap.isEmpty

  def toSeq: Seq[A] = sortedMap.values.toSeq.flatten

  override def toString: String = toSeq.mkString("PriorityQueue(", ", ", ")")

  override def hashCode(): Int = sortedMap.hashCode()

  override def equals(obj: Any): Boolean = Option(obj).collect {
    case q: PriorityQueue[A] => sortedMap == q.sortedMap
  }.getOrElse(false)
}


object PriorityQueue {

  def apply[A](elems: A*)(implicit ordering: Ordering[A]) =
    new PriorityQueue[A](SortedMap.empty(reversedOrdering)) ++ elems
}