package ru.vasily.utils

import ru.vasily.{FileContents, Runner}
import ru.vasily.simulation.TasksGenerator

class TestConfigPrinter(name: String, numberOfServers: Int, taskGenerator: TasksGenerator)
  extends Runner {

  def getResult = {
    val tasks = taskGenerator.generateTasks.sortWith(_.arrivalTime < _.arrivalTime)
    val header =
      """param NameOfTest :=  "%s";
        |param NW := %d;
        |param NK := %d;
        | """.stripMargin.format(
        name,
        numberOfServers,
        tasks.size
      )

    val workersPerformance = "param: perf :=\n" +
      (0 until numberOfServers).map("%d 1.0".format(_)).mkString("\n") + "\n;\n"

    val tasksExecutionTime = "param: tau :=\n" +
      tasks.zipWithIndex.map {
        case (task, i) => "%d %.1f".format(i, task.executionTime.toDouble)
      }.mkString("\n") + "\n;\n"

    val tasksArrivalTime = "param: TD :=\n" +
      tasks.zipWithIndex.map {
        case (task, i) => "%d %.1f".format(i, task.arrivalTime.toDouble)
      }.mkString("\n") + "\n;\n"

    val output = Seq(
      header,
      workersPerformance,
      tasksExecutionTime,
      tasksArrivalTime,
      "end;"
    ).mkString
    FileContents(output, "txt")
  }
}
