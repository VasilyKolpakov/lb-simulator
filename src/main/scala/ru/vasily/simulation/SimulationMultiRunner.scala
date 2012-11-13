package ru.vasily.simulation

import ru.vasily.{Serializer, Runner}

class SimulationMultiRunner(clusterModelFactoriesAndConfig: (Seq[(Seq[Double]) => ClusterModel], Seq[Any]),
                            serverPerformancesAndConfig: (Seq[Seq[Double]], Seq[Any]),
                            taskGeneratorsAndConfig: (Seq[TasksGenerator], Seq[Any])
                             ) extends Runner {
  def getResult = {
    val modelsFactoriesWithConfigs = unwrapAndZip(clusterModelFactoriesAndConfig)
    val serverPerformancesWithConfigs = unwrapAndZip(serverPerformancesAndConfig)
    val taskGensWithConfigs = unwrapAndZip(taskGeneratorsAndConfig)
    val result = for {
      (model, modelConfig) <- modelsFactoriesWithConfigs
      (servers, serversConfig) <- serverPerformancesWithConfigs
      (tasks, tasksConfig) <- taskGensWithConfigs
    } yield {
      val resultConfig = Map(
        "clusterModel" -> modelConfig,
        "servers" -> serversConfig,
        "taskGenerator" -> tasksConfig
      )
      val SimulationResult(performanceMetrics, uniformityMetrics, _) = new SimulationRunner(model(servers), tasks).getNonSerializedResult
      Map(
        "config" -> resultConfig,
        "result" -> Map(
          "performanceMetrics" -> performanceMetrics,
          "uniformityMetrics" -> uniformityMetrics
        )
      )
    }
    Serializer.marshal(result)
  }

  def unwrapAndZip[A, B](pair: (Seq[A], Seq[B])) = pair._1.zip(pair._2)
}
