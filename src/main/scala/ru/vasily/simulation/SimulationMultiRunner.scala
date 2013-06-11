package ru.vasily.simulation

import core.AgentId
import ru.vasily.{FileContents, Serializer, Runner}

class SimulationMultiRunner(clusterModelFactoriesAndConfig: (Seq[(Seq[Double], AgentId) => ClusterModel], Seq[Any]),
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
      val  history = ClusterModelRunner.getHistory(model(servers, _), tasks)
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
