package ru.vasily

import di._
import params.JsonDiLoader
import RichFile.enrichFile
import java.io.File
import simulation._
import simulation.core.AgentId
import simulation.DynamicRoundRobinSchedulerModel
import simulation.MonitoringServiceModel
import utils.TestConfigPrinter

object Main {
  // TODO: refactor to separate trait?
  type ModelFactory = (Seq[Double], AgentId) => ClusterModel
  val defaultMonitoringServiceModelInjector = Injector("DefaultMonitoring", MonitoringServiceModel(None))
  val monitoringServiceModelInjector = Injector("MonitoringAgents") {
    env => for {
      refreshTime <- env("refreshTime", classOf[Int])
    } yield MonitoringServiceModel(Some(refreshTime))
  }
  val simpleClusterModelInjector = Injector[ModelFactory]("SimpleCluster") {
    env => for {
      schedulerModel <- env("scheduler", classOf[SchedulerModel])
      monitoringServiceModel <- env("monitoring", classOf[MonitoringServiceModel])
    } yield (servers: Seq[Double], masterAgentId: AgentId) =>
        new SimpleClusterModel(servers, schedulerModel, monitoringServiceModel, masterAgentId)
  }

  val injectors = List[Injector[_]](
    // Runner
    ClassInjector("TestConfigPrinter", classOf[TestConfigPrinter],
      "testName", "servers", "taskGenerator"),
    Injector("MultiSimulation") {
      env => for {
        modelFactories <- env.seqWithConfig("clusterModels", classOf[ModelFactory])
        serversPerformances <- env.seqWithConfig("servers", classOf[Seq[Double]])
        taskGenerators <- env.seqWithConfig("taskGenerators", classOf[TasksGenerator])
      } yield new SimulationMultiRunner(modelFactories, serversPerformances, taskGenerators)
    },
    Injector("SingleSimulation") {
      env => for {
        model <- env("clusterModel", classOf[ModelFactory])
        serversPerformance <- env("servers", classOf[Seq[Double]])
        taskGenerator <- env("taskGenerator", classOf[TasksGenerator])
        outputFormat <- env("outputFormat", classOf[SimulationResultOutputFormat])
      } yield new SimulationRunner(model(serversPerformance, _), taskGenerator, outputFormat)
    },
    Injector("ComparingSimulation") {
      env => for {
        modelFactories <- env("clusterModels", classOf[Map[String, ModelFactory]])
        serversPerformance <- env("servers", classOf[Seq[Double]])
        taskGenerator <- env("taskGenerator", classOf[TasksGenerator])
        metricPath <- env("metricPath", classOf[Seq[String]])
      } yield {
        val models = modelFactories.mapValues(factory => factory(serversPerformance, _: AgentId))
        new ComparingRunner(models, taskGenerator, metricPath)
      }
    },

    // OutputFormats
    Injector("JSON", new JsonOutputFormat()),
    ClassInjector("SVG", classOf[SvgOutputFormat], "imageWidth", "makespan"),

    // ClusterModel
    Injector("RandomScheduler") {
      env => for {
        seed <- env("seed", classOf[Int])
      } yield (servers: Seq[Double], masterAgentId: AgentId) =>
          new SimpleClusterModel(servers, new RandomSchedulerModel(seed), MonitoringServiceModel(None), masterAgentId)
    },

    Injector("RoundRobinScheduler", (servers: Seq[Double], masterAgentId: AgentId) =>
      new SimpleClusterModel(servers, RoundRobinSchedulerModel, MonitoringServiceModel(None), masterAgentId)),

    Injector("MasterSlaveScheduler",
      (servers: Seq[Double], masterAgentId: AgentId) =>
        new SimpleClusterModel(servers, MasterWorkerSchedulerModel, MonitoringServiceModel(None), masterAgentId)),

    Injector("DynamicWRRScheduler") {
      env => for {
        maxWeigh <- env("maxWeight", classOf[Int])
        refreshTime <- env("refreshTime", classOf[Int])
      } yield {
        (servers: Seq[Double], masterAgentId: AgentId) =>
          new SimpleClusterModel(servers,
            DynamicRoundRobinSchedulerModel(maxWeigh, refreshTime),
            MonitoringServiceModel(Some(refreshTime)),
            masterAgentId)
      }
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

  case class Main(simulation: Runner) {
    def run(outputDir: File, outputFileName: String) {
      simulation.getResult match {
        case FileContents(contents, ext) => {
          val outputFile = new File(outputDir, s"$outputFileName.$ext")
          outputFile.text = contents
        }
        case ExecScript(script, ext, command) => {
          val scriptFile = new File(outputDir, s"$outputFileName.$ext")
          scriptFile.text = script(outputFileName)
          import scala.sys.process.Process
          Process(command(scriptFile.getName), outputDir).!
        }
      }
    }
  }

  def main(args: Array[String]) {
    for (inputFilePath <- args) {
      val inputFile = new File(inputFilePath)
      val diScope = JsonDiLoader.createDIScope(
        inputFile.text,
        injectors)
      val mainComponent = ComplexComponent(new ClassInjector("Main", classOf[Main], List("simulation")), diScope)
      ScopeDrivenDI.instantiate(mainComponent).run(inputFile.getParentFile, inputFile.getName + ".out")
    }
  }
}
