package ru.vasily.simulation

import core.AgentId
import ru.vasily.{FileContents, Serializer, Runner}
import ru.vasily.graphics._

class SimulationRunner(clusterModelFactory: AgentId => ClusterModel,
                       taskGenerator: TasksGenerator,
                       outputFormat: SimulationResultOutputFormat)
  extends Runner {

  def getResult = {
    val history = ClusterModelRunner.getHistory(clusterModelFactory, taskGenerator)
    outputFormat.format(history)
  }

}

trait SimulationResultOutputFormat {
  def format(history: Map[SimpleServer, Seq[TaskRecord]]): FileContents
}

class JsonOutputFormat extends SimulationResultOutputFormat {
  def format(history: Map[SimpleServer, Seq[TaskRecord]]) = {
    val metrics = new AlgorithmMetrics(history)
    val result = metrics.metricsMap + ("history" -> prettyHistory(history))
    val output = Serializer.marshal(result)
    FileContents(output, "js")
  }

  private def prettyHistory(history: Map[SimpleServer, Seq[TaskRecord]]) = history.mapValues {
    taskRecords =>
      taskRecords.map {
        case TaskRecord(Task(_, executionTime, arrivalTime), completionTime) =>
          Map("executionTime" -> executionTime, "arrivalTime " -> arrivalTime, "completionTime" -> completionTime)
      }
  }
}

class SvgOutputFormat(imageWidth: Int, expectedMakespan: Int) extends SimulationResultOutputFormat {
  def format(history: Map[SimpleServer, Seq[TaskRecord]]) = {
    val sortedHistory = history.mapValues(_.sortBy(_.completionTime)).toList.
      sortBy(_._1.indexNumber)

    implicit def longToInt(l: Long) = l.toInt

    val timeScale = imageWidth.toDouble / expectedMakespan

    def taskShape(taskIndex: Int, taskServerIndex: Int, startTime: Int, completionTime: Int) = {
      val rectColor = if (taskServerIndex % 2 == 0) Color.grayShade(200) else Color.grayShade(100)

      val scaledStartTime = (startTime * timeScale).toInt
      val scaledEndTime = (completionTime * timeScale).toInt

      val rect = Rectangle(scaledEndTime - scaledStartTime, 30, scaledStartTime, 0, rectColor)
      def borderLine(x: Int) = Line(x, -10, x, 40, Color.black)
      val center = (scaledEndTime + scaledStartTime) / 2
      val indexText = Text(taskIndex.toString, (center + 2): Int, 20)
      ComplexShape(
        Seq(
          rect,
          borderLine(scaledStartTime), borderLine(scaledEndTime),
          indexText
        ))
    }
    def serverHistoryToShape(serverId: SimpleServer, records: Seq[TaskRecord], taskIndexes: Map[TaskRecord, Int]) = {
      val taskShapes = records.zipWithIndex.map {
        case (record@TaskRecord(task, completionTime), taskIndex) => {
          val startTime = (completionTime - task.executionTime / serverId.serverPerformance).toInt
          taskShape(taskIndexes(record), taskIndex, startTime, completionTime)
        }
      }
      val textShift: Int = 55
      val text = Text("server # " + serverId.indexNumber, -textShift, 20)
      ComplexShape(text +: taskShapes, textShift + 55, serverId.indexNumber * 60 + 30)
    }

    def allHistoryShape(history: List[(SimpleServer, Seq[TaskRecord])]) = {
      val taskRecordIndexes = history.flatMap(_._2).sortBy {
        (record) => (record.task.arrivalTime, record.task.executionTime)
      }.zipWithIndex.toMap
      val shapes = history.map {
        case (serverId, taskRecords) =>
          serverHistoryToShape(serverId, taskRecords, taskRecordIndexes)
      }
      ComplexShape(shapes)
    }
    val shape = allHistoryShape(sortedHistory)
    val svgHtml = SVG.toSvgString(shape)
    FileContents(svgHtml, "svg")
  }
}
