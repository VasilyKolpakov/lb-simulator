package ru.vasily.simulation

import core.AgentId
import ru.vasily.{ExecScript, Runner}

class ComparingRunner(clusterModels: Map[String, AgentId => ClusterModel],
                      taskGenerator: TasksGenerator,
                      metricPath: Seq[String]) extends Runner {

  def getResult = {
    val Seq(metricType, metricKey) = metricPath
    val metrics = clusterModels.mapValues {
      modelFactory =>
        val (totalSimulationTime, history) = ClusterModelRunner.getHistory(modelFactory, taskGenerator)
        new AlgorithmMetrics(history, totalSimulationTime).metricsMap(metricType)(metricKey)
    }
    val (modelKeys, metricValues) = metrics.toList.unzip
    def argString(args: Seq[Any]) = args.mkString(", ")

    def code(outputFileNamePrefix: String) =
      """jpeg("%s.jpg")
        |data <- c(%s)
        |name <- c(%s)
        |barplot(data, names.arg = name, main = "%s")
        |dev.off()
        |q()
      """.stripMargin.format(
        outputFileNamePrefix,
        argString(metricValues),
        argString(modelKeys.map("\"" + _ + "\"")),
        metricKey
      )
    ExecScript(code, "r", (scriptFile) => "Rscript " + scriptFile)
  }
}
