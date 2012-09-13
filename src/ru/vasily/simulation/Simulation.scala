package ru.vasily.simulation

import collection.immutable
import collection.immutable.Stream._
import collection.immutable.SortedSet
import annotation.tailrec

object StreamUtils {
  implicit def enrichStream[T](stream: Stream[T]) = new RichStream[T](stream)
}

class RichStream[T](stream: Stream[T]) {
  def takeTill(endCondition: T => Boolean) = {
    def recursiveTakeTill(innerStream: Stream[T]): Stream[T] = {
      val head = innerStream.head
      if (endCondition(head)) {
        Stream.empty
      } else {
        Stream.cons(head, recursiveTakeTill(innerStream.tail))
      }
    }
    recursiveTakeTill(stream)
  }
}

class ReversedOrdering[T <: Ordered[T]] extends Ordering[T] {
  def compare(x: T, y: T) = {
    -x.compare(y)
  }
}

class ImmutablePriorityQueue[T] private(sortedSet: SortedSet[T]) {
  def head = sortedSet.head

  def dequeue = (head, new ImmutablePriorityQueue(sortedSet.tail))

  def ++(elements: Seq[T]) = new ImmutablePriorityQueue(sortedSet ++ elements)

  def isEmpty = sortedSet.isEmpty

  def toSeq = sortedSet.toSeq

  override def toString = sortedSet.toString()
}

object ImmutablePriorityQueue {
  def apply[T <: Ordered[T]](elements: T*): ImmutablePriorityQueue[T] = {
    new ImmutablePriorityQueue[T](SortedSet[T](elements: _*)(new ReversedOrdering[T]))
  }
}

class ModelState(agentIdToAgentStateMap: immutable.Map[AnyRef, AgentState],
                 messageQueue: ImmutablePriorityQueue[DeferredMessage], currentTime: Long = 0) {

  def nextState() = {
    if (messageQueue.isEmpty) {
      None
    } else {
      val (message, remainingMessages) = messageQueue.dequeue
      val nextTime: Long = message.timestamp
      if (nextTime < currentTime) {
        throw new RuntimeException("time consistency violated for message: " + message)
      }
      val agentId = message.receiverId
      val agentState = agentIdToAgentStateMap(agentId)
      val StateTransition(newAgentState, newMessages) = agentState.changeState(message)
      val nextAgentStates = agentIdToAgentStateMap.updated(agentId, newAgentState)
      val nextMessages = remainingMessages ++ newMessages
      Some(new ModelState(nextAgentStates, nextMessages, nextTime))
    }
  }

  def nextEventTime: Option[Long] =
    if (messageQueue.isEmpty) None else Some(messageQueue.head.timestamp)

  def agents = agentIdToAgentStateMap.toSeq

  def messages = messageQueue.toSeq

  override def toString = agentIdToAgentStateMap.toString() + "  " + messageQueue.toString
}

object ModelState {
  def apply(agentIdToAgentStateMap: immutable.Map[AnyRef, AgentState],
            initialMessages: Seq[DeferredMessage]) =
    new ModelState(agentIdToAgentStateMap, ImmutablePriorityQueue(initialMessages: _*))

  def main(args: Array[String]) {
    var queue = ImmutablePriorityQueue(
      DeferredMessage("a", 10, "a"),
      DeferredMessage("a", 30, "b"),
      DeferredMessage("a", 20, "c")
    )
    println(queue.toSeq)
    while (!queue.isEmpty) {
      val (head, tail) = queue.dequeue
      println(head)
      queue = tail
    }
  }
}
