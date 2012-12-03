package ru.vasily.simulation

import collection.immutable.Queue
import core._
import ru.vasily.simulation.MonitoringService.PostServerLoad
import ru.vasily.simulation.MonitoringService.Report

case class SimpleServer(indexNumber: Int, serverPerformance: Double, monitoringAgent: AgentId) extends AgentId {

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
      case LoadLevelRequest() => newActions(serverLoadInfo(monitoringAgent, taskQueue.size))
    }

    def completeCurrentTask(currentTime: Long) = {
      val (TaskMessage(messageSender, completedTask), queueTail) = taskQueue.dequeue
      val reportMessage = monitoringMessage(completedTask, currentTime)
      val callbackMessage = SendMessage.withoutDelay(ServerFinishedTask(thisAgent), messageSender)
      val messages = List(reportMessage, callbackMessage)
      if (queueTail.isEmpty) {
        val monitoringAgentMessage = SendMessage.withoutDelay(TurnOff(), monitoringAgent)
        StateTransition(IdleState(), monitoringAgentMessage :: messages)
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
        val monitoringAgentMessage = SendMessage.withoutDelay(TurnOn(thisAgent), monitoringAgent)
        newState(BusyState(taskMessage),
          selfMessage,
          monitoringAgentMessage)
      }
      case LoadLevelRequest() => newActions(SendMessage.withoutDelay(LoadLevelResponse(0), monitoringAgent))
    }
  }

}

object SimpleServer {
  def generateAgents(serversPerformance: Seq[Double], refreshTime: Option[Int] = None) = {
    val monitoringAgents = (0 until serversPerformance.size).map {
      index =>
        val agent = MonitoringAgent(index, refreshTime)
        Agent(agent, agent.Off())
    }
    val servers = serversPerformance.zipWithIndex.zip(monitoringAgents) map {
      case ((performance, index), monitoringAgent) => {
        val agentId = SimpleServer(index, performance, monitoringAgent.id)
        Agent(agentId, agentId.IdleState())
      }
    }

    (servers, monitoringAgents)
  }
}


case class MonitoringAgent(serverIndex: Int, refreshTime: Option[Int]) extends AgentId {

  case class On(serverId: AgentId) extends AgentState {
    def changeState(currentTime: Long, message: AnyRef) = message match {
      case Tic() => newActions(
        SendMessage(
          message = Message(Tic(), thisAgent),
          delay = refreshTime.get
        ),
        SendMessage.withoutDelay(LoadLevelRequest(), serverId)
      )
      case LoadLevelResponse(loadLevel) => newActions(
        SendMessage.withoutDelay(PostServerLoad(serverId, loadLevel), MonitoringService)
      )
      case TurnOff() => newState(Off())
    }
  }

  case class Off() extends AgentState {
    def changeState(currentTime: Long, message: AnyRef) = message match {

      case TurnOn(serverId) if refreshTime.isDefined =>
        newState(On(serverId),
          SendMessage.withoutDelay(Tic(), thisAgent)
        )
      case _ => noChanges
    }
  }

}

case class LoadLevelRequest()

case class LoadLevelResponse(loadLevel: Int)

case class Tic()

case class TurnOff()

case class TurnOn(serverId: SimpleServer)

case class TaskMessage(sender: AgentId, task: Task)

case class TaskCompleted()

case class ServerFinishedTask(serverId: AgentId)

