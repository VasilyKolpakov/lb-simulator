package ru.vasily.simulation

class AlgorithmMetrics(history: Map[SimpleServer, Seq[TaskRecord]], makespan: Long) {

  def performanceMetrics = Map(
    "average slowdown" -> averageSlowdownMetric,
    "min-max slowdown" -> minMaxSlowdownMetric,
    "average utilization" -> averageUtilization,
    "makespan" -> makespan.toDouble
  )

  def uniformityMetrics = Map(
    "min-max utilization" -> minMaxMetric,
    "standard deviation" -> standardDeviation,
    "coefficient of variation" -> coefficientOfVariation,
    "Jain index" -> JainsIndex,
    "balancing efficiency" -> balancingEfficiency
  )

  def metricsMap = Map(
    "performanceMetrics" -> performanceMetrics,
    "uniformityMetrics" -> uniformityMetrics
  )

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
