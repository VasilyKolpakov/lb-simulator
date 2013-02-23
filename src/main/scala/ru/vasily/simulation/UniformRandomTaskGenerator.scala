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
        i,
        random.nextInt(maxExecutionTime - minExecutionTime + 1) + minExecutionTime,
        random.nextInt(latestArrivalTime + 1)
      )
    }
  }
}
