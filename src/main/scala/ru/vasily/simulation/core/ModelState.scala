package ru.vasily.simulation.core

import collection.immutable

class ModelState(agentIdToAgentStateMap: immutable.Map[AgentId, AgentState],
                 messageQueue: MessageQueue[Message, MessageTagRecord, Long],
                 val timeOfLastEvent: Long = 0) {

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
      val nextMessages = ModelState.enqueueActions(remainingMessages, messageActions, agentId, nextEventTime)
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

  type MQueue = MessageQueue[Message, MessageTagRecord, Long]

  def apply(agents: Seq[Agent]) = {
    val messageQueue = agents.foldLeft(MessageQueue[Message, MessageTagRecord, Long](0)) {
      case (queue, Agent(agentId, _, initialActions)) =>
        enqueueActions(queue, initialActions, agentId, 0)
    }
    val agentsMap = agents.map(agent => (agent.id, agent.initialState)).toMap
    new ModelState(agentsMap, messageQueue)
  }

  def enqueueActions(queue: MQueue, messageActions: Seq[MessageAction], agentId: AgentId, currentTime: Long): MQueue =
    messageActions.foldLeft(queue)(addActionToQueue(agentId, currentTime))

  private def addActionToQueue(agentId: AgentId, currentTime: Long)
                              (queue: MQueue, action: MessageAction) = action match {
    case SendMessage(message, delay, tags) =>
      queue.enqueue(message, tags.map(MessageTagRecord(_, agentId)), currentTime + delay)
    case CancelMessages(tags) => queue.cancelMessages(tags.map(MessageTagRecord(_, agentId)))
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
}

