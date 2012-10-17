package ru.vasily.simulation

import util.Random

class UniformRandomTaskGenerator(numberOfTasks: Int,
                                 latestArrivalTime: Int,
                                 minExecutionTime: Int,
                                 maxExecutionTime: Int,
                                 seed: Int)
  extends TasksGenerator {
  val random = new Random(seed)

  def generateTasks =
    for (i <- 1 to numberOfTasks) yield {
      Task(
        random.nextInt(maxExecutionTime - minExecutionTime) + minExecutionTime,
        random.nextInt(latestArrivalTime)
      )
    }
}
