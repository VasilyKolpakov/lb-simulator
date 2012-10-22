package ru.vasily

import di._
import params.JsonDiLoader
import RichFile.enrichFile
import java.io.File
import simulation._
import simulation.DynamicRoundRobin
import simulation.DynamicRoundRobin
import simulation.MasterWorkerClusterModel
import simulation.MasterWorkerClusterModel
import simulation.RandomClusterModel
import simulation.RandomClusterModel
import simulation.RoundRobinClusterModel
import simulation.RoundRobinClusterModel
import utils.TestConfigPrinter
import java.math.SignedMutableBigInteger

object Main {
  val injectors = List[Injector[_]](
    // Runner
    ClassInjector("TestConfigPrinter", classOf[TestConfigPrinter],
      "testName", "servers", "taskGenerator"),
    Injector("MultiRunner") {
      env => for {
        models <- env.seqWithConfig("clusterModels", classOf[Seq[Double] => ClusterModel])
        serversPerformances <- env.seqWithConfig("servers", classOf[Seq[Double]])
        taskGenerators <- env.seqWithConfig("taskGenerators", classOf[TasksGenerator])
      } yield new SimulationMultiRunner(models, serversPerformances, taskGenerators)
    },
    Injector("Runner") {
      env => for {
        model <- env("clusterModel", classOf[(Seq[Double]) => ClusterModel])
        serversPerformance <- env("servers", classOf[Seq[Double]])
        taskGenerator <- env("taskGenerator", classOf[TasksGenerator])
      } yield new SimulationRunner(model(serversPerformance), taskGenerator, true)
    },

    // ClusterModel
    Injector("DynamicWRR") {
      env => for {
        maxWeigh <- env("maxWeight", classOf[Int])
      } yield {
        (servers: Seq[Double]) => DynamicRoundRobin(servers, maxWeigh)
      }
    },
    Injector("MasterSlave") {
      env =>
        ((servers: Seq[Double]) => MasterWorkerClusterModel(servers), Map())
    },
    Injector("Random") {
      env =>
        ((servers: Seq[Double]) => RandomClusterModel(servers), Map())
    },
    Injector("RoundRobin") {
      env =>
        ((servers: Seq[Double]) => RoundRobinClusterModel(servers), Map())
    },

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
      outputFile.text = runner.getResultString
    }
  }
}
