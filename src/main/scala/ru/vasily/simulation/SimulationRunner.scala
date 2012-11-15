package ru.vasily.simulation

import ru.vasily.{Serializer, Runner}
import ru.vasily.graphics._

class SimulationRunner(clusterModel: ClusterModel, taskGenerator: TasksGenerator, outputFormat: SimulationResultOutputFormat) extends Runner {
  def getResult: String = {
    val (totalSimulationTime, history) = HistoryGetter.getHistory(clusterModel, taskGenerator)
    outputFormat.format(history, totalSimulationTime)
  }

}

trait SimulationResultOutputFormat {
  def format(history: Map[SimpleServer, Seq[TaskRecord]], makespan: Long): String
}

class JsonOutputFormat extends SimulationResultOutputFormat {
  def format(history: Map[SimpleServer, Seq[TaskRecord]], makespan: Long) = {
    val metrics = new AlgorithmMetrics(history, makespan)
    val result = metrics.metricsMap + ("history" -> prettyHistory(history))
    Serializer.marshal(result)
  }

  private def prettyHistory(history: Map[SimpleServer, Seq[TaskRecord]]) = history.mapValues {
    taskRecords =>
      taskRecords.map {
        case TaskRecord(Task(executionTime, arrivalTime), completionTime) =>
          Map("executionTime" -> executionTime, "arrivalTime " -> arrivalTime, "completionTime" -> completionTime)
      }
  }
}

class SvgOutputFormat extends SimulationResultOutputFormat {
  def format(history: Map[SimpleServer, Seq[TaskRecord]], makespan: Long) = {
    val sortedHistory = history.mapValues(_.sortBy(_.completionTime)).toList.
      sortBy(_._1.indexNumber)

    implicit def longToInt(l: Long) = l.toInt

    val timeScale = 1000.0 / makespan

    def taskShape(taskIndex: Int, startTime: Int, completionTime: Int) = {
      val rectColor = if (taskIndex % 2 == 0) Color.grayShade(200) else Color.grayShade(100)

      val scaledStartTime = (startTime * timeScale).toInt
      val scaledEndTime = (completionTime * timeScale).toInt

      val rect = Rectangle(scaledEndTime - scaledStartTime, 30, scaledStartTime, 0, rectColor)
      def borderLine(x: Int) = Line(x, -10, x, 40, Color.black)
      ComplexShape(
        Seq(
          rect,
          borderLine(scaledStartTime), borderLine(scaledEndTime)
        ))
    }
    def serverHistoryToShape(serverId: SimpleServer, records: Seq[TaskRecord]) = {
      val taskShapes = records.zipWithIndex.map {
        case (TaskRecord(Task(execTime, _), completionTime), taskIndex) => {
          val startTime = (completionTime - execTime / serverId.serverPerformance).toInt
          taskShape(taskIndex, startTime, completionTime)
        }
      }
      val textShift: Int = 110
      val text = Text("server # " + serverId.indexNumber, -textShift, 20)
      ComplexShape(text +: taskShapes, textShift, serverId.indexNumber * 60 + 30)
    }

    def allHistoryShape(history: List[(SimpleServer, Seq[TaskRecord])]) = {
      val shapes = history.map {
        case (serverId, taskRecords) =>
          serverHistoryToShape(serverId, taskRecords)
      }
      ComplexShape(shapes)
    }
    val shape = allHistoryShape(sortedHistory)
    SVG.toSvgString(shape)
  }
}
