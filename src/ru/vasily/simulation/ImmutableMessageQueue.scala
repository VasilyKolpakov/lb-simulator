package ru.vasily.simulation

import collection.immutable.SortedMap
import ru.vasily.simulation.ImmutableMessageQueue.OrderedTimestamp

class ImmutableMessageQueue[M] private(sortedMap: SortedMap[OrderedTimestamp, M]) {

  def enqueue(timestamp: Long, message: M): ImmutableMessageQueue[M] = {
    val maximumOrderedTimestamp = OrderedTimestamp(timestamp, Integer.MAX_VALUE)
    val mapProjectionTillTimestamp: SortedMap[OrderedTimestamp, M] = sortedMap.to(maximumOrderedTimestamp)
    if (mapProjectionTillTimestamp.isEmpty || mapProjectionTillTimestamp.lastKey.timestamp != timestamp) {
      new ImmutableMessageQueue(sortedMap.updated(OrderedTimestamp(timestamp, 0), message))
    } else {
      val lastEqualTimestampOrder = mapProjectionTillTimestamp.lastKey.order
      new ImmutableMessageQueue(sortedMap.updated(OrderedTimestamp(timestamp, lastEqualTimestampOrder + 1), message))
    }
  }

  def head = {
    val (OrderedTimestamp(timestamp, _), message) = sortedMap.head
    (timestamp, message)
  }

  def dequeue: ((Long, M), ImmutableMessageQueue[M]) = {
    (head, new ImmutableMessageQueue(sortedMap.tail))
  }

  def ++(timestampedMessages: Seq[(Long, M)]): ImmutableMessageQueue[M] = {

    def enqueue(queue: ImmutableMessageQueue[M], timestampAndMessage: (Long, M)) = {
      val (timestamp, message) = timestampAndMessage
      queue.enqueue(timestamp, message)
    }
    val self: ImmutableMessageQueue[M] = this
    timestampedMessages.foldLeft(self)(enqueue)
  }

  def isEmpty: Boolean = sortedMap.isEmpty

  def toSeq: Seq[(Long, M)] =

    sortedMap.toSeq map {
      case (OrderedTimestamp(timestamp, _), message) => (timestamp, message)
    }
}


object ImmutableMessageQueue {

  case class OrderedTimestamp(timestamp: Long, order: Int) extends Ordered[OrderedTimestamp] {
    def compare(that: OrderedTimestamp) = {
      val priorityComparison = timestamp.compare(that.timestamp)
      if (priorityComparison == 0) {
        order.compare(that.order)
      } else {
        priorityComparison
      }
    }
  }

  class ReversedOrdering[T <: Ordered[T]] extends Ordering[T] {
    def compare(x: T, y: T) = {
      -x.compare(y)
    }
  }

  def apply[M](prioritizedElements: (Long, M)*) = new ImmutableMessageQueue[M](SortedMap.empty) ++ prioritizedElements
}