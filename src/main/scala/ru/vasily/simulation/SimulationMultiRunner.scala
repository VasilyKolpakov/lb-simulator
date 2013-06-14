package ru.vasily.simulation

import ru.vasily.{FileContents, Serializer, Runner}

class SimulationMultiRunner(schedulerModelsAndConfig: (Seq[SchedulerModel], Seq[Any]),
                            serverPerformancesAndConfig: (Seq[Seq[Double]], Seq[Any]),
                            taskGeneratorsAndConfig: (Seq[TasksGenerator], Seq[Any])
                             ) extends Runner {

  def getResult = {
    val schedulerModelsWithConfigs = unwrapAndZip(schedulerModelsAndConfig)
    val serverPerformancesWithConfigs = unwrapAndZip(serverPerformancesAndConfig)
    val taskGensWithConfigs = unwrapAndZip(taskGeneratorsAndConfig)
    val result = for {
      (scheduler, schedulerConfig) <- schedulerModelsWithConfigs
      (servers, serversConfig) <- serverPerformancesWithConfigs
      (tasks, tasksConfig) <- taskGensWithConfigs
    } yield {
      val resultConfig = Map(
        "scheduler" -> schedulerConfig,
        "servers" -> serversConfig,
        "taskGenerator" -> tasksConfig
      )
      val clusterModel = new SimpleClusterModel(servers, scheduler)
      val history = ClusterModelRunner.getHistory(clusterModel, tasks)
      val metricsMap = new AlgorithmMetrics(history).metricsMap
      Map(
        "config" -> resultConfig,
        "result" -> metricsMap
      )
    }
    val output = Serializer.marshal(result)
    FileContents(output, "js")
  }

  def unwrapAndZip[A, B](pair: (Seq[A], Seq[B])) = pair._1.zip(pair._2)
}
