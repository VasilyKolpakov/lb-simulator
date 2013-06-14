package ru.vasily

import di._
import params.JsonDiLoader
import RichFile.enrichFile
import java.io.File
import simulation._
import simulation.DynamicRoundRobinSchedulerModel
import simulation.MonitoringServiceModel
import utils.TestConfigPrinter

object Main {
  // TODO: refactor to separate trait?
  val injectors = List[Injector[_]](
    // Runner
    ClassInjector("TestConfigPrinter", classOf[TestConfigPrinter],
      "testName", "servers", "taskGenerator"),
    Injector("MultiSimulation") {
      env => for {
        schedulers <- env.seqWithConfig("schedulers", classOf[SchedulerModel])
        serversPerformances <- env.seqWithConfig("servers", classOf[Seq[Double]])
        taskGenerators <- env.seqWithConfig("taskGenerators", classOf[TasksGenerator])
      } yield new SimulationMultiRunner(schedulers, serversPerformances, taskGenerators)
    },
    Injector("SingleSimulation") {
      env => for {
        model <- env("clusterModel", classOf[ClusterModel])
        taskGenerator <- env("taskGenerator", classOf[TasksGenerator])
        outputFormat <- env("outputFormat", classOf[SimulationResultOutputFormat])
      } yield new SimulationRunner(model, taskGenerator, outputFormat)
    },
    Injector("ComparingSimulation") {
      env => for {
        models <- env("clusterModels", classOf[Map[String, ClusterModel]])
        taskGenerator <- env("taskGenerator", classOf[TasksGenerator])
        metricPath <- env("metricPath", classOf[Seq[String]])
      } yield {
        new ComparingRunner(models, taskGenerator, metricPath)
      }
    },

    // OutputFormats
    Injector("JSON", new JsonOutputFormat()),
    ClassInjector("SVG", classOf[SvgOutputFormat], "imageWidth", "makespan"),

    // ClusterModel
    Injector[ClusterModel]("SimpleCluster") {
      env => for {
        schedulerModel <- env("scheduler", classOf[SchedulerModel])
        servers <- env("servers", classOf[Seq[Double]])
      } yield new SimpleClusterModel(servers, schedulerModel)
    },

    Injector("RandomScheduler") {
      env => for {
        seed <- env("seed", classOf[Int])
      } yield new RandomSchedulerModel(seed)
    },

    Injector("RoundRobinScheduler", RoundRobinSchedulerModel),

    Injector("MasterSlaveScheduler", MasterWorkerSchedulerModel),

    Injector("DynamicWRRScheduler") {
      env => for {
        maxWeigh <- env("maxWeight", classOf[Int])
        monitoringModel <- env("monitoring", classOf[MonitoringServiceModel])
      } yield DynamicRoundRobinSchedulerModel(maxWeigh, monitoringModel)
    },

    Injector("PeriodicMonitoring") {
      env => for {
        refreshTime <- env("refreshTime", classOf[Int])
      } yield MonitoringServiceModel(refreshTime)
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
      try {
        val diScope = JsonDiLoader.createDIScope(
          inputFile.text,
          injectors)
        val mainComponent = ComplexComponent(new ClassInjector("Main", classOf[Main], List("simulation")), diScope)
        val main = ScopeDrivenDI.instantiate(mainComponent)
        main.run(inputFile.getParentFile, inputFile.getName + ".out")
      } catch {
        case e: Exception => throw new RuntimeException(s"$inputFilePath simulation failed", e)
      }
    }
  }
}
