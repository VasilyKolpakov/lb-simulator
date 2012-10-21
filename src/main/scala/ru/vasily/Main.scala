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
    ClassInjector("MultiRunner", classOf[SimulationMultiRunner],
      "clusterModels", "taskGenerator"),
    ClassInjector("Runner", classOf[SimulationRunner],
      "clusterModel", "taskGenerator"),

    // ClusterModel
    ClassInjector("DynamicWRR", classOf[DynamicRoundRobin],
      "servers", "maxWeight"),
    ClassInjector("MasterSlave", classOf[MasterWorkerClusterModel],
      "servers"),
    ClassInjector("Random", classOf[RandomClusterModel],
      "servers"),
    ClassInjector("RoundRobin", classOf[RoundRobinClusterModel],
      "servers"),

    // TasksGenerator
    ClassInjector("RandomTaskGen", classOf[TestConfigPrinter],
      "tasks", "maxArrivalTime", "minExecTime", "maxExecTime", "seed")
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
