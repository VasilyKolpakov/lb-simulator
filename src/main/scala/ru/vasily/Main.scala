package ru.vasily

import di.{DIScope, ComplexComponent, Environment, Injector}
import params.JsonDiLoader
import RichFile.enrichFile
import java.io.File
import simulation._
import utils.TestConfigPrinter

object Main {
  val injectors = Map(
    "TestConfigPrinter" -> new Injector[Runner] {
      def create(env: Environment) = new TestConfigPrinter(
        env("testName"), env("servers"), env("taskGenerator")
      )
    },
    "MultiRunner" -> new Injector[Runner] {
      def create(env: Environment) = new SimulationMultiRunner(
        env("clusterModels"), env("taskGenerator")
      )
    },
    "Runner" -> new Injector[Runner] {
      def create(env: Environment) = new SimulationRunner(
        env("clusterModel"), env("taskGenerator")
      )
    },
    "DynamicWRR" -> new Injector[ClusterModel] {
      def create(env: Environment) = new DynamicRoundRobin(env("servers"), env("maxWeight"))
    },
    "MasterSlave" -> new Injector[ClusterModel] {
      def create(env: Environment) = new MasterWorkerClusterModel(env("servers"))
    },
    "Random" -> new Injector[ClusterModel] {
      def create(env: Environment) = new RandomClusterModel(env("servers"))
    },
    "RoundRobin" -> new Injector[ClusterModel] {
      def create(env: Environment) = new RoundRobinClusterModel(env("servers"))
    },
    "RandomTaskGen" -> new Injector[TasksGenerator] {
      def create(env: Environment) = new UniformRandomTaskGenerator(
        env("tasks"), env("maxArrivalTime"), env("minExecTime"), env("maxExecTime"), env("seed")
      )
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
