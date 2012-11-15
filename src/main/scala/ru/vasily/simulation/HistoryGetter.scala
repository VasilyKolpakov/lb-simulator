package ru.vasily.simulation

import core.{DelayedMessage, ModelState, Message}

object HistoryGetter {
  def getHistory(clusterModel: ClusterModel, taskGenerator: TasksGenerator) = {
    val initialMessagesReceiver = clusterModel.initialMessagesReceiver
    val agents = clusterModel.agents
    val tasks = taskGenerator.generateTasks
    val taskMessages = for (task <- tasks) yield {
      val taskMessage = Message(task, initialMessagesReceiver)
      DelayedMessage(taskMessage, task.arrivalTime)
    }

    val initialModelState = ModelState(agents, taskMessages)

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
