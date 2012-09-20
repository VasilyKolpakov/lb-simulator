package ru.vasily.simulation

import collection.immutable
import collection.immutable.Stream._
import collection.immutable.SortedSet
import annotation.tailrec
import sun.swing.plaf.synth.Paint9Painter.PaintType
import runtime.RichLong

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


class ModelState(agentIdToAgentStateMap: immutable.Map[AgentId, AgentState],
                 messageQueue: ImmutableMessageQueue[Message], val timeOfLastEvent: Long = 0) {

  def nextState() = {
    if (messageQueue.isEmpty) {
      None
    } else {
      val ((nextEventTime, message), remainingMessages) = messageQueue.dequeue
      if (nextEventTime < timeOfLastEvent) {
        throw new RuntimeException("time consistency violated for message: " + message
          + " current time: " + timeOfLastEvent + " message timestamp: " + nextEventTime)
      }
      val agentId = message.receiverId
      val agentState = agentIdToAgentStateMap(agentId)
      val StateTransition(newAgentState, newMessages) = agentState.changeState(nextEventTime, message.contents)
      val nextAgentStates = agentIdToAgentStateMap.updated(agentId, newAgentState)
      val prioritizedMessages = newMessages map {
        ModelState.delayedMessageToQueueElement(_, nextEventTime)
      }
      val nextMessages = remainingMessages ++ prioritizedMessages
      Some(new ModelState(nextAgentStates, nextMessages, nextEventTime))
    }
  }

  def agents = agentIdToAgentStateMap.toSeq

  def messages = messageQueue.toSeq

  def timeOfNextEvent = if (messageQueue.isEmpty) {
    None
  } else {
    Some(messageQueue.head._1)
  }

  override def toString = agentIdToAgentStateMap.toString() + "  " + messageQueue.toString
}

object ModelState {
  def apply(agents: Seq[Pair[AgentId, AgentState]], initialMessages: Seq[DelayedMessage]) = {
    val queueElements = initialMessages map {
      delayedMessageToQueueElement(_, currentTime = 0)
    }
    new ModelState(Map(agents: _*), ImmutableMessageQueue(queueElements: _*))
  }

  private def delayedMessageToQueueElement(dm: DelayedMessage, currentTime: Long) = (dm.delay + currentTime, dm.message)
}

