package ru.vasily.simulation

import util.Random

class UniformRandomTaskGenerator(numberOfTasks: Int,
                                 latestArrivalTime: Int,
                                 minExecutionTime: Int,
                                 maxExecutionTime: Int,
                                 seed: Int)
  extends TasksGenerator {

  def generateTasks = {
    val random = new Random(seed)
    for (i <- 1 to numberOfTasks) yield {
      Task(
        random.nextInt(maxExecutionTime - minExecutionTime) + minExecutionTime,
        random.nextInt(latestArrivalTime)
      )
    }
  }
}
