package ru.vasily

import di._
import params.JsonDiLoader
import RichFile.enrichFile
import java.io.File
import simulation._
import simulation.core.AgentId
import simulation.DynamicRoundRobin
import simulation.MasterWorkerClusterModel
import simulation.RandomClusterModel
import simulation.RoundRobinClusterModel
import utils.TestConfigPrinter

object Main {
  // TODO: refactor to separate trait?
  type ModelFactory = (Seq[Double], AgentId) => ClusterModel

  val injectors = List[Injector[_]](
    // Runner
    ClassInjector("TestConfigPrinter", classOf[TestConfigPrinter],
      "testName", "servers", "taskGenerator"),
    Injector("MultiRunner") {
      env => for {
        modelFactories <- env.seqWithConfig("clusterModels", classOf[ModelFactory])
        serversPerformances <- env.seqWithConfig("servers", classOf[Seq[Double]])
        taskGenerators <- env.seqWithConfig("taskGenerators", classOf[TasksGenerator])
      } yield new SimulationMultiRunner(modelFactories, serversPerformances, taskGenerators)
    },
    Injector("Runner") {
      env => for {
        model <- env("clusterModel", classOf[ModelFactory])
        serversPerformance <- env("servers", classOf[Seq[Double]])
        taskGenerator <- env("taskGenerator", classOf[TasksGenerator])
        outputFormat <- env("outputFormat", classOf[SimulationResultOutputFormat])
      } yield new SimulationRunner(model(serversPerformance, _), taskGenerator, outputFormat)
    },
    Injector("ComparingRunner") {
      env => for {
        modelFactories <- env("clusterModels", classOf[Map[String, ModelFactory]])
        serversPerformance <- env("servers", classOf[Seq[Double]])
        taskGenerator <- env("taskGenerator", classOf[TasksGenerator])
        metricPath <- env("metricPath", classOf[Seq[String]])
      } yield {
        val models = modelFactories.mapValues(factory => factory(serversPerformance, _:AgentId))
        new ComparingRunner(models, taskGenerator, metricPath)
      }
    },

    // OutputFormats
    Injector("JSON", new JsonOutputFormat()),
    ClassInjector("SVG", classOf[SvgOutputFormat], "imageWidth", "makespan"),

    // ClusterModel
    Injector("DynamicWRR") {
      env => for {
        maxWeigh <- env("maxWeight", classOf[Int])
        refreshTime <- env("refreshTime", classOf[Int])
      } yield {
        (servers: Seq[Double], masterAgentId: AgentId) => DynamicRoundRobin(servers, maxWeigh, refreshTime, masterAgentId)
      }
    },
    Injector("MasterSlave", (servers: Seq[Double], masterAgentId: AgentId) => MasterWorkerClusterModel(servers, masterAgentId)),
    Injector("Random") {
      env => for {
        seed <- env("seed", classOf[Int])
      } yield (servers: Seq[Double], masterAgentId: AgentId) => RandomClusterModel(servers, seed, masterAgentId)
    },
    Injector("RoundRobin", (servers: Seq[Double], masterAgentId: AgentId) => RoundRobinClusterModel(servers, masterAgentId)),

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
    for (inputFilePath <- args) {
      val inputFile = new File(inputFilePath)
      val sdComponent = JsonDiLoader.createSDComponent(
        inputFile.text,
        injectors,
        defaultRootType = "Runner")
      val runner = sdComponent.instance.asInstanceOf[Runner]
      runner.getResult match {
        case FileContents(contents, ext) => {
          val outputFile = new File(inputFilePath + ".out." + ext)
          outputFile.text = contents
        }
        case ExecScript(script, ext, command) => {
          val inputFileDir: File = inputFile.getParentFile
          val inputFileName = inputFile.getName
          val scriptFile = new File(inputFileDir, inputFileName + ".out." + ext)
          scriptFile.text = script(inputFileName + ".out")
          import scala.sys.process.Process
          Process(command(scriptFile.getName), inputFileDir).!
        }
      }
    }
  }
}
