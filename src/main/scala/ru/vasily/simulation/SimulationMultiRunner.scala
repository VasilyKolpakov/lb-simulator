package ru.vasily.simulation

import ru.vasily.{Serializer, Runner}

class SimulationMultiRunner(clusterModelFactoriesAndConfig: (Seq[(Seq[Double]) => ClusterModel], Seq[Any]),
                            serverPerformancesAndConfig: (Seq[Seq[Double]], Seq[Any]),
                            taskGeneratorsAndConfig: (Seq[TasksGenerator], Seq[Any]),
                            outputFormat: String
                             ) extends Runner {
  def getResult = {
    val modelsFactoriesWithConfigs = clusterModelFactoriesAndConfig.zipped.toList
    val serverPerformancesWithConfigs = serverPerformancesAndConfig.zipped.toList
    val taskGensWithConfigs = taskGeneratorsAndConfig.zipped.toList
    val allResults = for {
      (servers, serversConfig) <- serverPerformancesWithConfigs
      (tasks, tasksConfig) <- taskGensWithConfigs
    } yield {

      val config = Map(
        "servers" -> serversConfig,
        "taskGenerator" -> tasksConfig
      )
      val results = for ((model, modelConfig) <- modelsFactoriesWithConfigs)
      yield {
        val result = new SimulationRunner(model(servers), tasks, false).getResult
        Map("clusterModel" -> modelConfig, "result" -> result)
      }
      Map("config" -> config, "results" -> results)
    }
    if (outputFormat == "json") {
      Serializer.marshal(allResults)
    } else {
      val configStrings = allResults.map {
        result =>
          Serializer.marshal(result("config")).replace("\n", "")
      }
      val clusterModelsAndResults = allResults.map {
        result => {
          val results = result("results").asInstanceOf[List[Map[String, Any]]]
          results.map {
            map => (map("clusterModel").asInstanceOf[Map[String, Any]], map("result").asInstanceOf[SimulationResult])
          }
        }
      }
      val (perfMetricsKeys, unifMetricsKeys) = {
        val simResult = clusterModelsAndResults.head.head._2
        (simResult.performanceMetrics.keys, simResult.uniformityMetrics.keys)
      }
      val tableHeader = (List("modelType") ++ perfMetricsKeys ++ unifMetricsKeys).mkString("\t")
      val tablesString = configStrings.zip(clusterModelsAndResults).flatMap {
        case (config, clusterModelAndResult) => {
          val tableName = config
          val rowsString = clusterModelAndResult.map {
            case (modelConfig, result) => {
              val modelType = modelConfig("type").asInstanceOf[String]
              val perfMetrics = perfMetricsKeys.toList.map(result.performanceMetrics(_).formatted("%.3f"))
              val unifMetrics = unifMetricsKeys.toList.map(result.uniformityMetrics(_).formatted("%.3f"))
              (List(modelType) ++ perfMetrics ++ unifMetrics).mkString("\t")
            }
          }
          (List(tableName) ++ rowsString)
        }
      }.mkString("\n")
      tableHeader + "\n" + tablesString
    }
  }

  override def getResultString = getResult.asInstanceOf[String]
}
