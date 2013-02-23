package ru.vasily.simulation

import collection.immutable.Queue
import core._
import ru.vasily.simulation.MonitoringService.PostServerLoad
import ru.vasily.simulation.MonitoringService.Report

case class SimpleServer(indexNumber: Int, serverPerformance: Double) extends AgentId {

  def taskCompletedMessage(task: Task) = {
    val executionTime: Long = (task.executionTime / serverPerformance).toInt
    SendMessage(
      message = Message(TaskCompleted(), this),
      delay = executionTime
    )
  }

  def monitoringMessage(task: Task, time: Long) = {
    val report = Report(this, TaskRecord(task, time))
    SendMessage.withoutDelay(report, MonitoringService)
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
      val monitoringMsg = monitoringMessage(completedTask, currentTime)
      val callbackMessage = SendMessage.withoutDelay(TaskFinished(thisAgent, completedTask), messageSender)
      val messages = List(monitoringMsg, callbackMessage)
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
        val selfMessage = taskCompletedMessage(taskMessage.task)
        newState(BusyState(taskMessage), selfMessage)
      }
      case LoadLevelRequest(monitoringAgent) => newActions(SendMessage.withoutDelay(LoadLevelResponse(0), monitoringAgent))
    }
  }

}

object SimpleServer {
  def generateAgents(serversPerformance: Seq[Double], refreshTime: Option[Int] = None) = {
    val servers = serversPerformance.zipWithIndex.map {
      case (performance, index) => {
        val agentId = SimpleServer(index, performance)
        Agent(agentId, agentId.IdleState())
      }
    }

    val monitoringAgents = servers.map {
      agent =>
        val agentId = MonitoringAgent(agent.id, refreshTime)
        Agent(agentId, agentId.State, SendMessage.withoutDelay(Tic, agentId))
    }

    (servers, monitoringAgents)
  }
}


case class MonitoringAgent(serverId: AgentId, refreshTime: Option[Int]) extends AgentId {

  case object State extends AgentState {
    def changeState(currentTime: Long, message: AnyRef) = message match {
      case Tic =>
        if (refreshTime.isDefined) {
          newActions(
            SendMessage(
              message = Message(Tic, thisAgent),
              delay = refreshTime.get
            ),
            SendMessage.withoutDelay(LoadLevelRequest(thisAgent), serverId)
          )
        } else {
          noChanges
        }
      case LoadLevelResponse(loadLevel) => newActions(
        SendMessage.withoutDelay(PostServerLoad(serverId, loadLevel), MonitoringService)
      )
    }
  }

}

case class LoadLevelRequest(requester: AgentId)

case class LoadLevelResponse(loadLevel: Int)

case object Tic

case class TaskMessage(sender: AgentId, task: Task)

case class TaskCompleted()

case class TaskFinished(agentId: AgentId, task: Task) {
  def forward(receiverId: AgentId) = SendMessage.withoutDelay(TaskFinished(agentId, task), receiverId)
}

