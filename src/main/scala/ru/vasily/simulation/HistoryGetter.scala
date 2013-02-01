package ru.vasily.simulation

import core._
import core.Message

object HistoryGetter {
  def getHistory(clusterModel: ClusterModel, taskGenerator: TasksGenerator) = {
    val initialMessagesReceiver = clusterModel.initialMessagesReceiver
    val tasks = taskGenerator.generateTasks
    val messageActions = for (task <- tasks) yield {
      val taskMessage = Message(task, initialMessagesReceiver)
      SendMessage(taskMessage, task.arrivalTime)
    }
    case class SinkAgent() extends AgentId
    case class DummyState() extends AgentState {
      def changeState(currentTime: Long, message: AnyRef): StateTransition[AgentState] = noChanges
    }
    val sinkAgent = Agent(SinkAgent(), DummyState(), messageActions.toList)
    val initialModelState = ModelState(sinkAgent +: clusterModel.agents)

    //    for (state <- initialModelState.nextStates) {
    //      println(ModelState.prettyToString(state))
    //    }

    val lastModelState = initialModelState.nextStates.last
    val history = lastModelState.agents.get(MonitoringService).collect {
      case serviceState: MonitoringService.State => serviceState.serversHistory.toList.collect {
        case (server: SimpleServer, taskRecords) => (server, taskRecords)
      }.toMap
    }.getOrElse {
      throw new RuntimeException("MonitoringService is not present in cluster model " + clusterModel)
    }

    val totalSimulationTime = lastModelState.timeOfLastEvent

    (totalSimulationTime, history)
  }
}
