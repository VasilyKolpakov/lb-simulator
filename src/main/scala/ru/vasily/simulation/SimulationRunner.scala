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
    val history = lastModelState.agents.get(MonitoringService).collect {
      case serviceState: MonitoringService.State => serviceState.serversHistory.toList.collect {
        case (server: SimpleServer, taskRecords) => (server, taskRecords)
      }.toMap
    }.getOrElse {
      throw new RuntimeException("MonitoringService is not present in cluster model " + clusterModel)
    }

    val totalSimulationTime = lastModelState.timeOfLastEvent
    val metrics = new AlgorithmMetrics(history, totalSimulationTime)
    val performanceMetrics = Map(
      "average slowdown" -> metrics.averageSlowdownMetric,
      "min-max slowdown" -> metrics.minMaxSlowdownMetric,
      "average utilization" -> metrics.averageUtilization,
      "makespan" -> totalSimulationTime.toDouble
    )

    val uniformityMetrics = Map(
      "min-max utilization" -> metrics.minMaxMetric,
      "standard deviation" -> metrics.standardDeviation,
      "coefficient of variation" -> metrics.coefficientOfVariation,
      "Jain index" -> metrics.JainsIndex,
      "balancing efficiency" -> metrics.balancingEfficiency
    )
    // TODO investigate compiler hang
    val prettyHistory = history.mapValues {
      taskRecords =>
        taskRecords.map {
          case TaskRecord(Task(executionTime, arrivalTime), completionTime) =>
            Map("executionTime" -> executionTime, "arrivalTime " -> arrivalTime, "completionTime" -> completionTime)
        }
    }
    SimulationResult(performanceMetrics, uniformityMetrics, if (showHistory) {
      prettyHistory
    } else {
      Map.empty
    })
  }

  class AlgorithmMetrics(history: Map[SimpleServer, Seq[TaskRecord]], makespan: Long) {

    val slowdowns = for ((server, taskRecords) <- history;
                         taskRecord <- taskRecords;
                         task = taskRecord.task)
    yield (taskRecord.completionTime - task.arrivalTime).toDouble /
        (task.executionTime / server.serverPerformance)

    val serversUtilization = for ((server, taskRecords) <- history) yield {
      taskRecords.map(_.task.executionTime).sum.toDouble / (makespan * server.serverPerformance)
    }

    def averageUtilization = serversUtilization.sum / serversUtilization.size

    def averageSlowdownMetric = {
      val taskSlowdowns = slowdowns
      taskSlowdowns.sum / taskSlowdowns.size
    }

    def minMaxSlowdownMetric = {
      val taskSlowdowns = slowdowns
      taskSlowdowns.max / taskSlowdowns.min
    }

    def minMaxMetric = serversUtilization.min / serversUtilization.max

    def standardDeviation = {
      val size: Int = serversUtilization.size
      val average = serversUtilization.sum / size
      val squareDeviation =
        serversUtilization.map(x => (x - average) * (x - average)).sum / size
      math.sqrt(squareDeviation)
    }

    def coefficientOfVariation = standardDeviation / averageUtilization

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

  }

}

case class SimulationResult(performanceMetrics: Map[String, Double],
                            uniformityMetrics: Map[String, Double],
                            history: Map[SimpleServer, Seq[Map[String, Long]]])
