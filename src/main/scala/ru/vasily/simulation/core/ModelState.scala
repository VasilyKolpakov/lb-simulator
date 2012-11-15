package ru.vasily.simulation.core

import collection.immutable

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
  def apply(agents: Seq[Agent[AgentId, AgentState]], initialMessages: Seq[DelayedMessage] = Nil) = {
    val queueElements = (initialMessages ++ agents.flatMap(_.initialMessages)) map {
      delayedMessageToQueueElement(_, currentTime = 0)
    }
    val agentsMap = agents.map(agent => (agent.id, agent.initialState)).toMap
    new ModelState(agentsMap, ImmutableMessageQueue(queueElements: _*))
  }

  def prettyToString(modelState: ModelState) = {
    val lines =
      Seq("time of last event: " + modelState.timeOfLastEvent,
        "time of next event: " + modelState.timeOfNextEvent.getOrElse("-"),
        "agents:") ++
        (for (agent <- modelState.agents) yield agent.toString) ++
        Seq("messages:") ++
        modelState.messages.map {
          case (timestamp, message) =>
            "time: %d, msg: %s".format(timestamp, message.toString)
        } ++
        Seq("======================")
    lines.mkString("\n")
  }

  private def delayedMessageToQueueElement(dm: DelayedMessage, currentTime: Long) = (dm.delay + currentTime, dm.message)
}

