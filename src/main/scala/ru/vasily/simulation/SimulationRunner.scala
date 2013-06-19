package ru.vasily.simulation

import ru.vasily.{FileContents, Serializer, Runner}
import ru.vasily.graphics._
import collection.immutable.ListMap

class SimulationRunner(clusterModelFactory: ClusterModel,
                       taskGenerator: TasksGenerator,
                       outputFormat: SimulationResultOutputFormat)
  extends Runner {

  def getResult = {
    val history = ClusterModelRunner.getHistory(clusterModelFactory, taskGenerator)
    outputFormat.format(history)
  }

}

trait SimulationResultOutputFormat {
  def format(history: Seq[ServerHistory]): FileContents
}

class JsonOutputFormat extends SimulationResultOutputFormat {
  def format(history: Seq[ServerHistory]) = {
    val metrics = new AlgorithmMetrics(history)
    val result = metrics.metricsMap + ("history" -> historyToSerializableMap(history))
    val output = Serializer.marshal(result)
    FileContents(output, "js")
  }

  private def historyToSerializableMap(history: Seq[ServerHistory]) = {
    val serverIdStringAndRecordsPairs = history.map {
      case ServerHistory(serverId, performance, taskRecords) =>
        val records = taskRecords.map {
          case TaskRecord(Task(_, executionTime, arrivalTime), completionTime) =>
            Map("executionTime" -> executionTime, "arrivalTime " -> arrivalTime, "completionTime" -> completionTime)
        }
        (serverId.toString, records)
    }.sortBy(_._1)
    ListMap(serverIdStringAndRecordsPairs: _*)
  }
}

class SvgOutputFormat(imageWidth: Int, expectedMakespan: Int) extends SimulationResultOutputFormat {
  def format(history: Seq[ServerHistory]) = {
    val sortedHistory = history.sortBy(_.serverId.toString)

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
    def serverHistoryToShape(serverHistory: ServerHistory, serverIndex: Int, taskIndexes: Map[TaskRecord, Int]) = {
      val ServerHistory(_, serverPerformance, records) = serverHistory
      val taskShapes = records.zipWithIndex.map {
        case (record@TaskRecord(task, completionTime), taskIndex) => {
          val startTime = (completionTime - task.executionTime / serverPerformance).toInt
          taskShape(taskIndexes(record), taskIndex, startTime, completionTime)
        }
      }
      val textShift: Int = 55
      val text = Text("server # " + serverIndex, -textShift, 20)
      ComplexShape(text +: taskShapes, textShift + 55, serverIndex * 60 + 30)
    }

    def allHistoryShape(history: Seq[ServerHistory]) = {
      val taskRecordIndexes = history.flatMap(_.taskRecords).sortBy {
        (record) => (record.task.arrivalTime, record.task.executionTime)
      }.zipWithIndex.toMap
      val shapes = history.zipWithIndex.map {
        case (serverHistory: ServerHistory, serverIndex) =>
          serverHistoryToShape(serverHistory, serverIndex, taskRecordIndexes)
      }
      ComplexShape(shapes)
    }
    val shape = allHistoryShape(sortedHistory)
    val svgHtml = SVG.toSvgString(shape)
    FileContents(svgHtml, "svg")
  }
}
