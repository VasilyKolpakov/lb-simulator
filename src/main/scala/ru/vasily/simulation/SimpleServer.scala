package ru.vasily.simulation

import collection.immutable.Queue
import core._
import ru.vasily.simulation.MonitoringService.Report
import util.Random

case class SimpleServer(indexNumber: Int, serverPerformance: Double) extends AgentId {

  private case class TaskCompleted()

  def taskCompletedMessage(task: Task) = {
    val executionTime: Long = (task.executionTime / serverPerformance).toInt
    SendMessage(
      message = Message(TaskCompleted(), this),
      delay = executionTime
    )
  }

  def serverLoadInfo(agentId: AgentId, load: Int) = SendMessage.withoutDelay(LoadLevelResponse(load), agentId)

  object BusyState {
    def apply(taskMessages: TaskMessage*): BusyState = BusyState(Queue(taskMessages: _*))
  }

  case class BusyState(taskQueue: Queue[TaskMessage]) extends AgentState {

    def changeState(currentTime: Long, message: AnyRef) = message match {
      case taskMessage: TaskMessage => newState(BusyState(taskQueue.enqueue(taskMessage)))

      case TaskCompleted() => completeCurrentTask(currentTime)
      case LoadLevelRequest(monitoringAgent) => newActions(serverLoadInfo(monitoringAgent, taskQueue.size))
    }

    def completeCurrentTask(currentTime: Long) = {
      val (TaskMessage(messageSender, completedTask), queueTail) = taskQueue.dequeue
      val log = LogAction(TaskRecord(completedTask, currentTime))
      val callbackMessage = SendMessage.withoutDelay(TaskFinished(thisAgent, completedTask), messageSender)
      if (queueTail.isEmpty) {
        newState(IdleState(),
          callbackMessage,
          log)
      } else {
        newState(BusyState(queueTail),
          taskCompletedMessage(queueTail.head.task),
          callbackMessage,
          log)
      }
    }
  }

  case class IdleState() extends AgentState {
    def changeState(currentTime: Long, message: AnyRef) = message match {
      case taskMessage: TaskMessage => {
        val selfMessage = taskCompletedMessage(taskMessage.task)
        newState(BusyState(taskMessage), selfMessage)
      }
      case LoadLevelRequest(monitoringAgent) => newActions(SendMessage.withoutDelay(LoadLevelResponse(0), monitoringAgent))
    }
  }

}

object SimpleServer {

  def generateServers(serversPerformance: Seq[Double]): Seq[Agent] =
    serversPerformance.zipWithIndex.map {
      case (performance, index) => {
        val agentId = SimpleServer(index, performance)
        Agent(agentId, agentId.IdleState())
      }
    }

  def generateRandomServers(numberOfServers: Int, maxPerf: Double, minPerf: Double, seed: Int) = {
    val random = new Random(seed)
    val performance = List.fill(numberOfServers) {
      random.nextDouble() * (maxPerf - minPerf) + minPerf
    }
    generateServers(performance)
  }

}

case class LoadLevelRequest(requester: AgentId)

case class LoadLevelResponse(loadLevel: Int)

case class TaskMessage(sender: AgentId, task: Task)


case class TaskFinished(executorId: AgentId, task: Task) {
  def forward(receiverId: AgentId) = SendMessage.withoutDelay(TaskFinished(executorId, task), receiverId)
}

