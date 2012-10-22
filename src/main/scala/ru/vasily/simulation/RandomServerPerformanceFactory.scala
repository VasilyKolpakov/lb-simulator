package ru.vasily.simulation

import util.Random

object RandomServerPerformanceFactory {
  def getServersPerformance(numberOfServers: Int, maxPerf: Double, minPerf: Double, seed: Int) = {
    val random = new Random(seed)
    List.fill(numberOfServers) {
      random.nextDouble() * (maxPerf - minPerf) + minPerf
    }
  }

}
