package ru.vasily.simulation.core

import collection.immutable

class ModelState(agentIdToAgentStateMap: immutable.Map[AgentId, AgentState],
                 messageQueue: MessageQueue, val timeOfLastEvent: Long = 0) {

  def nextState = messageQueue.dequeueOption.map {
    case ((nextEventTime, message), remainingMessages) =>
      if (nextEventTime < timeOfLastEvent) {
        throw new RuntimeException("time consistency violated for message: " + message
          + " current time: " + timeOfLastEvent + " message timestamp: " + nextEventTime)
      }
      val agentId = message.receiverId
      val agentState = agentIdToAgentStateMap(agentId)
      val StateTransition(newAgentState, messageActions) = agentState.changeState(nextEventTime, message.contents)
      val nextAgentStates = agentIdToAgentStateMap.updated(agentId, newAgentState)
      val nextMessages = remainingMessages ++ messageActions
      new ModelState(nextAgentStates, nextMessages, nextEventTime)
  }

  def agents = agentIdToAgentStateMap

  def messages = messageQueue.messagesSeq

  def timeOfNextEvent = messageQueue.timeOfNextEventOption

  def nextStates: Stream[ModelState] = nextState match {
    case Some(state) => Stream.cons(state, state.nextStates)
    case None => Stream.empty
  }

  override def toString = agentIdToAgentStateMap.toString() + "  " + messageQueue.toString
}

object ModelState {
  def apply(agents: Seq[Agent[AgentId, AgentState]], initialMessages: Seq[SendMessage] = Nil) = {
    val agentMessages = for {
      agent <- agents
      message <- agent.messageActions.asInstanceOf[Seq[SendMessage]]
    } yield message

    val queueElements = initialMessages ++ agentMessages
    val agentsMap = agents.map(agent => (agent.id, agent.initialState)).toMap
    new ModelState(agentsMap, MessageQueue(queueElements: _*))
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

  private def delayedMessageToQueueElement(sm: SendMessage, currentTime: Long) = (sm.delay + currentTime, sm.message)
}

