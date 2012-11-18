package ru.vasily.simulation

import ru.vasily.{ExecScript, Runner}

class ComparingRunner(clusterModels: Map[String, ClusterModel],
                      taskGenerator: TasksGenerator,
                      metricPath: Seq[String]) extends Runner {

  def getResult = {
    val Seq(metricType, metricKey) = metricPath
    val metrics = clusterModels.mapValues {
      model =>
        val (totalSimulationTime, history) = HistoryGetter.getHistory(model, taskGenerator)
        new AlgorithmMetrics(history, totalSimulationTime).metricsMap(metricType)(metricKey)
    }
    val (modelKeys, metricValues) = metrics.toList.unzip
    def argString(args: Seq[Any]) = args.mkString(", ")

    def code(outputFileNamePrefix: String) =
      """jpeg("%s.jpg")
        |data <- c(%s)
        |name <- c(%s)
        |barplot(data, names.arg = name)
        |dev.off()
        |q()
      """.stripMargin.format(
        outputFileNamePrefix,
        argString(metricValues),
        argString(modelKeys.map("\"" + _ + "\""))
      )
    ExecScript(code, "r", (scriptFile) => "Rscript " + scriptFile)
  }
}
