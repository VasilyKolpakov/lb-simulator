package ru.vasily

import di.{Environment, Injector}
import params.JsonDiLoader
import RichFile.enrichFile
import java.io.File
import simulation._

object Main {
  val injectors = Map(
    "DynamicWRR" -> new Injector[ClusterModel] {
      def create(env: Environment) = new DynamicRoundRobin(env("servers"),env("maxWeight"))
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
  val simulationRunnerInj = new Injector[SimulationRunner] {
    def create(env: Environment) = new SimulationRunner(
      env("clusterModel"), env("taskGenerator")
    )
  }

  def main(args: Array[String]) {
    for (fileName <- args) {
      val inputFile = new File(fileName)
      val outputFile = new File(fileName + ".out")
      val runner = JsonDiLoader.createDIScope(inputFile.text, injectors)
        .accept(simulationRunnerInj)
      outputFile.text = Serializer.marshal(runner.getResult)
    }
  }
}
