package ru.vasily.simulation

import ru.vasily.{Serializer, Runner}

class SimulationRunner(clusterModel: ClusterModel, taskGenerator: TasksGenerator, showHistory: Boolean = true) extends Runner {
  def getResult: AnyRef = {
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
    val MonitoringService.State(history) = lastModelState.agents.get(MonitoringService).getOrElse {
      throw new RuntimeException("MonitoringService is not present in cluster model " + clusterModel)
    }

    val totalSimulationTime = lastModelState.timeOfLastEvent
    val uniMetrics = new UniformityMetrics(history, totalSimulationTime)
    val performanceMetrics = Map("performance metrics" -> Map(
      "average slowdown metric" -> averageSlowdownMetric(history),
      "min-max slowdown metric" -> minMaxSlowdownMetric(history),
      "makespan" -> totalSimulationTime
    ))
    val uniformityMetrics = Map("uniformity metrics" -> Map(
      "min-max metric" -> uniMetrics.minMaxMetric,
      "standard deviation" -> uniMetrics.standardDeviation,
      "Jain index" -> uniMetrics.JainsIndex,
      "balancing efficiency" -> uniMetrics.balancingEfficiency
    ))
    // TODO investigate compiler hang
    val historyList =
      if (showHistory) {
        List(Map("history" -> history))
      } else {
        Nil
      }
    List[AnyRef](performanceMetrics, uniformityMetrics) ++ historyList
  }

  def averageSlowdownMetric(history: Map[AgentId, Seq[TaskRecord]]): Double = {
    val taskSlowdowns = slowdowns(history)
    taskSlowdowns.sum / taskSlowdowns.size
  }

  def minMaxSlowdownMetric(history: Map[AgentId, Seq[TaskRecord]]): Double = {
    val taskSlowdowns = slowdowns(history)
    taskSlowdowns.max / taskSlowdowns.min
  }

  def slowdowns(history: Map[AgentId, Seq[TaskRecord]]) = for ((_, taskRecords) <- history;
                                                               taskRecord <- taskRecords;
                                                               task = taskRecord.task)
  yield (taskRecord.completionTime - task.arrivalTime).toDouble / task.executionTime


  class UniformityMetrics(history: Map[AgentId, Seq[TaskRecord]], makespan: Long) {


    def minMaxMetric = serversUtilization.min / serversUtilization.max


    def standardDeviation = {
      val size: Int = serversUtilization.size
      val average = serversUtilization.sum / size
      val squareDeviation =
        serversUtilization.map(x => (x - average) * (x - average)).sum / size
      math.sqrt(squareDeviation)
    }

    def JainsIndex = {
      val utilSum: Double = serversUtilization.sum
      val sumSquared = utilSum * utilSum
      val squareSummed = serversUtilization.map(x => x * x).sum
      sumSquared / squareSummed / serversUtilization.size
    }

    def balancingEfficiency = {
      val average = serversUtilization.sum / serversUtilization.size
      average / serversUtilization.max
    }

    val serversUtilization: Iterable[Double] = {
      for ((_, taskRecords) <- history;
           taskRecord <- taskRecords;
           task = taskRecord.task)
      yield task.executionTime.toDouble / makespan
    }
  }

}
