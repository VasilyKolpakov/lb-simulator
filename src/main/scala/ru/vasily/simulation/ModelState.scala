package ru.vasily.simulation

import collection.immutable
import collection.immutable.Stream._
import collection.immutable.SortedSet
import annotation.tailrec
import sun.swing.plaf.synth.Paint9Painter.PaintType
import runtime.RichLong

class ModelState(agentIdToAgentStateMap: immutable.Map[AgentId, AgentState],
                 messageQueue: ImmutableMessageQueue[Message], val timeOfLastEvent: Long = 0) {

  def nextState = {
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

  def agents = agentIdToAgentStateMap

  def messages = messageQueue.toSeq

  def timeOfNextEvent = if (messageQueue.isEmpty) {
    None
  } else {
    Some(messageQueue.head._1)
  }

  def nextStates: Stream[ModelState] = nextState match {
    case Some(state) => Stream.cons(state, state.nextStates)
    case None => Stream.empty
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

  def prettyToString(modelState: ModelState) = {
    val lines =
      Seq("time of last event: " + modelState.timeOfLastEvent,
        "time of next event: " + modelState.timeOfNextEvent.getOrElse("-"),
        "agents:") ++
        (for (agent <- modelState.agents) yield agent.toString) ++
        Seq("messages:") ++
        (for (message <- modelState.messages) yield message.toString) ++
        Seq("======================")
    lines.mkString("\n")
  }

  private def delayedMessageToQueueElement(dm: DelayedMessage, currentTime: Long) = (dm.delay + currentTime, dm.message)
}

