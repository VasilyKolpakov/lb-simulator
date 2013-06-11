package ru.vasily.simulation

import core._
import core.Message

object ClusterModelRunner {

  case object SinkAgent extends AgentId

  case class State(taskIdsSet: Set[Any]) extends AgentState {
    def changeState(currentTime: Long, message: AnyRef) = message match {
      case msg: TaskFinished => newState(State(taskIdsSet - msg.task.id))
    }

    def isAllTasksAreFinished = taskIdsSet.isEmpty
  }

  def isFinalModelState(state: ModelState) = state.agents(SinkAgent).asInstanceOf[State].isAllTasksAreFinished

  def getHistory(clusterModelFactory: AgentId => ClusterModel, taskGenerator: TasksGenerator) = {

    val clusterModel = clusterModelFactory(SinkAgent)
    val initialMessagesReceiver = clusterModel.initialMessagesReceiver
    val tasks = taskGenerator.generateTasks
    val messageActions = for (task <- tasks) yield {
      val taskMessage = Message(task, initialMessagesReceiver)
      SendMessage(taskMessage, task.arrivalTime)
    }
    val sinkAgent = Agent(SinkAgent, State(tasks.map(_.id).toSet), messageActions.toList)
    val initialModelState = ModelState(sinkAgent +: clusterModel.agents)

    //    for (state <- initialModelState.nextStates) {
    //      println(ModelState.prettyToString(state))
    //    }

    initialModelState
      .logsUntil(isFinalModelState)
      .collect {case Log(serverId: SimpleServer, time, record: TaskRecord) => (serverId, record)}
      .toList
      .groupBy {case (serverId, record) => serverId}
      .mapValues(_.map {case (serverId, record) => record})
  }
}
