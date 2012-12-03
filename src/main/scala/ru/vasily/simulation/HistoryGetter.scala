package ru.vasily.simulation

import core.{SendMessage, ModelState, Message}

object HistoryGetter {
  def getHistory(clusterModel: ClusterModel, taskGenerator: TasksGenerator) = {
    val initialMessagesReceiver = clusterModel.initialMessagesReceiver
    val agents = clusterModel.agents
    val tasks = taskGenerator.generateTasks
    val messageActions = for (task <- tasks) yield {
      val taskMessage = Message(task, initialMessagesReceiver)
      SendMessage(taskMessage, task.arrivalTime)
    }

    val initialModelState = ModelState(agents, messageActions)

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
