package ru.vasily

import di._
import params.JsonDiLoader
import RichFile.enrichFile
import java.io.File
import simulation._
import simulation.DynamicRoundRobin
import simulation.MasterWorkerClusterModel
import simulation.RandomClusterModel
import simulation.RoundRobinClusterModel
import utils.TestConfigPrinter

object Main {
  val injectors = List[Injector[_]](
    // Runner
    ClassInjector("TestConfigPrinter", classOf[TestConfigPrinter],
      "testName", "servers", "taskGenerator"),
    Injector("MultiRunner") {
      env => for {
        modelFactories <- env.seqWithConfig("clusterModels", classOf[Seq[Double] => ClusterModel])
        serversPerformances <- env.seqWithConfig("servers", classOf[Seq[Double]])
        taskGenerators <- env.seqWithConfig("taskGenerators", classOf[TasksGenerator])
      } yield new SimulationMultiRunner(modelFactories, serversPerformances, taskGenerators)
    },
    Injector("Runner") {
      env => for {
        model <- env("clusterModel", classOf[(Seq[Double]) => ClusterModel])
        serversPerformance <- env("servers", classOf[Seq[Double]])
        taskGenerator <- env("taskGenerator", classOf[TasksGenerator])
        outputFormat <- env("outputFormat", classOf[SimulationResultOutputFormat])
      } yield new SimulationRunner(model(serversPerformance), taskGenerator, outputFormat)
    },
    Injector("ComparingRunner") {
      env => for {
        modelFactories <- env("clusterModels", classOf[Map[String, Seq[Double] => ClusterModel]])
        serversPerformance <- env("servers", classOf[Seq[Double]])
        taskGenerator <- env("taskGenerator", classOf[TasksGenerator])
        metricPath <- env("metricPath", classOf[Seq[String]])
      } yield {
        val models = modelFactories.mapValues(factory => factory(serversPerformance))
        new ComparingRunner(models, taskGenerator, metricPath)
      }
    },

    // OutputFormats
    Injector("JSON", new JsonOutputFormat()),
    Injector("SVG", new SvgOutputFormat()),

    // ClusterModel
    Injector("DynamicWRR") {
      env => for {
        maxWeigh <- env("maxWeight", classOf[Int])
        refreshTime <- env("refreshTime", classOf[Int])
      } yield {
        (servers: Seq[Double]) => DynamicRoundRobin(servers, maxWeigh, refreshTime)
      }
    },
    Injector("MasterSlave", (servers: Seq[Double]) => MasterWorkerClusterModel(servers)),
    Injector("Random", (servers: Seq[Double]) => RandomClusterModel(servers)),
    Injector("RoundRobin", (servers: Seq[Double]) => RoundRobinClusterModel(servers)),

    // TasksGenerator
    ClassInjector("RandomTaskGen", classOf[UniformRandomTaskGenerator],
      "tasks", "maxArrivalTime", "minExecTime", "maxExecTime", "seed"),

    // ServersPerformance
    Injector("RandomPerformance") {
      env => for {
        servers <- env("numberOfServers", classOf[Int])
        maxPerf <- env("maxPerf", classOf[Int])
        minPerf <- env("minPerf", classOf[Int])
        seed <- env("seed", classOf[Int])
      } yield RandomServerPerformanceFactory.getServersPerformance(servers, maxPerf, minPerf, seed)
    }

  )

  def main(args: Array[String]) {
    for (fileName <- args) {
      val inputFile = new File(fileName)
      val outputFile = new File(fileName + ".out")

      val sdComponent = JsonDiLoader.createSDComponent(
        inputFile.text,
        injectors,
        defaultRootType = "Runner")
      val runner = sdComponent.instance.asInstanceOf[Runner]
      outputFile.text = runner.getResult
    }
  }
}
