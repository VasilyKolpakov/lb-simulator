package ru.vasily.simulation

import core._
import core.Message

object ClusterModelRunner {

  case class State(taskIdsSet: Set[Any]) extends AgentState {
    def changeState(currentTime: Long, message: AnyRef) = message match {
      case msg: TaskFinished => newState(State(taskIdsSet - msg.task.id))
    }

    def isAllTasksAreFinished = taskIdsSet.isEmpty
  }

  val rootAgentId = AgentId("root")

  def isFinalModelState(state: ModelState) = state.agents(rootAgentId).asInstanceOf[State].isAllTasksAreFinished

  def getHistory(clusterModel: ClusterModel, taskGenerator: TasksGenerator) = {

    val ClusterAgents(clusterModelAgents, initialMessagesReceiver) = clusterModel.agents(rootAgentId)
    val tasks = taskGenerator.generateTasks
    val messageActions = for (task <- tasks) yield {
      val taskMessage = Message(task, initialMessagesReceiver)
      SendMessage(taskMessage, task.arrivalTime)
    }
    val rootAgent = Agent(rootAgentId, State(tasks.map(_.id).toSet), messageActions.toList)
    val initialModelState = ModelState(rootAgent +: clusterModelAgents)

    //    for (state <- initialModelState.nextStates) {
    //      println(ModelState.prettyToString(state))
    //    }

    val logs: Stream[Log] = initialModelState.logsUntil(isFinalModelState)
    val taskRecords = logs
      .collect {case Log(serverId, time, record: TaskRecord) => (serverId, record)}
      .toList
      .groupBy {case (serverId, record) => serverId}
      .mapValues(_.map {case (serverId, record) => record})
    logs.collect {
      case Log(serverId, _, ServerInfo(serverPerformance)) =>
        ServerHistory(serverId, serverPerformance, taskRecords.get(serverId).getOrElse(Seq()))
    }
  }
}

case class ServerHistory(serverId: AgentId, performance: Double, taskRecords: Seq[TaskRecord])