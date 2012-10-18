package ru.vasily.simulation

import ru.vasily.Runner

class SimulationMultiRunner(clusterModels: Map[String, ClusterModel], taskGenerator: TasksGenerator) extends Runner {
  def getResult = {
    clusterModels map {
      case (key, model) => (key + " " + model.toString, new SimulationRunner(model, taskGenerator, false).getResult)
    }
  }
}
