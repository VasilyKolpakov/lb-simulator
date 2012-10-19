package ru.vasily.simulation

import ru.vasily.{Serializer, Runner}

class SimulationMultiRunner(clusterModels: Map[String, ClusterModel], taskGenerator: TasksGenerator) extends Runner {
  def getResult = {
    val result = clusterModels map {
      case (key, model) => (key + " " + model.toString, new SimulationRunner(model, taskGenerator, false).getResult)
    }
    Serializer.marshal(result)
  }
}
