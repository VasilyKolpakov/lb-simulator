package ru.vasily.simulation

import collection.immutable.Queue
import ru.vasily.simulation.MonitoringService.Report

case class SimpleServer(indexNumber: Int, serverPerformance: Double) extends AgentId {

  def taskCompletedMessage(task: Task) = {
    val executionTime: Long = (task.executionTime / serverPerformance).toInt
    DelayedMessage(TaskCompleted(), this, executionTime)
  }

  def monitoringMessage(task: Task, time: Long) = {
    val report = Report(this, TaskRecord(task, time))
    DelayedMessage(report, MonitoringService, 0)
  }

  object BusyState {
    def apply(taskMessages: TaskMessage*): BusyState = BusyState(Queue(taskMessages: _*))
  }

  case class BusyState(taskQueue: Queue[TaskMessage]) extends AgentState {

    def changeState(currentTime: Long, message: AnyRef) = message match {
      case taskMessage: TaskMessage => StateTransition(BusyState(taskQueue.enqueue(taskMessage)))

      case TaskCompleted() => completeCurrentTask(currentTime)
    }

    def completeCurrentTask(currentTime: Long) = {
      val (TaskMessage(messageSender, completedTask), queueTail) = taskQueue.dequeue
      val reportMessage = monitoringMessage(completedTask, currentTime)
      val callbackMessage = DelayedMessage(ServerFinishedTask(thisAgent), messageSender, 0)
      val messages = List(reportMessage, callbackMessage)
      if (queueTail.isEmpty) {
        StateTransition(IdleState(), messages)
      } else {
        StateTransition(BusyState(queueTail),
          taskCompletedMessage(queueTail.head.task) :: messages)
      }
    }
  }

  case class IdleState() extends AgentState {
    def changeState(currentTime: Long, message: AnyRef) = message match {
      case taskMessage: TaskMessage => {
        val newState = BusyState(taskMessage)
        val newMessage = taskCompletedMessage(taskMessage.task)
        StateTransition(newState, newMessage)
      }
    }
  }
}

object SimpleServer {
  def generateServers(serversPerformance: Seq[Double]) = serversPerformance.zipWithIndex map {
    case (performance, index) => SimpleServer(index, performance)
  }
}

case class TaskMessage(sender: AgentId, task: Task)

case class TaskCompleted()

case class ServerFinishedTask(serverId: AgentId)

