package ru.vasily.simulation

import collection.immutable.Queue
import ru.vasily.simulation.MonitoringService.{PostServerLoad, Report}

case class SimpleServer(indexNumber: Int, serverPerformance: Double, monitoringAgent: MonitoringAgent) extends AgentId {

  def taskCompletedMessage(task: Task) = {
    val executionTime: Long = (task.executionTime / serverPerformance).toInt
    DelayedMessage(TaskCompleted(), this, executionTime)
  }

  def monitoringMessage(task: Task, time: Long) = {
    val report = Report(this, TaskRecord(task, time))
    DelayedMessage(report, MonitoringService, 0)
  }

  def serverLoadInfo(agentId: AgentId, load: Int) = DelayedMessage(LoadLevelResponse(load), agentId, 0)

  object BusyState {
    def apply(taskMessages: TaskMessage*): BusyState = BusyState(Queue(taskMessages: _*))
  }

  case class BusyState(taskQueue: Queue[TaskMessage]) extends AgentState {

    def changeState(currentTime: Long, message: AnyRef) = message match {
      case taskMessage: TaskMessage => StateTransition(BusyState(taskQueue.enqueue(taskMessage)))

      case TaskCompleted() => completeCurrentTask(currentTime)
      case LoadLevelRequest(sender) => {
        sendMessages(serverLoadInfo(sender, taskQueue.size))
      }
    }

    def completeCurrentTask(currentTime: Long) = {
      val (TaskMessage(messageSender, completedTask), queueTail) = taskQueue.dequeue
      val reportMessage = monitoringMessage(completedTask, currentTime)
      val callbackMessage = DelayedMessage(ServerFinishedTask(thisAgent), messageSender, 0)
      val messages = List(reportMessage, callbackMessage)
      if (queueTail.isEmpty) {
        val monitoringAgentMessage = DelayedMessage(TurnOff(), monitoringAgent, 0)
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
        val monitoringAgentMessage = DelayedMessage(TurnOn(thisAgent), monitoringAgent, 0)
        newState(BusyState(taskMessage),
          selfMessage,
          monitoringAgentMessage)
      }
    }
  }

}

object SimpleServer {
  def generateAgents(serversPerformance: Seq[Double], refreshTime: Int = 0) = {
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


case class MonitoringAgent(serverIndex: Int, refreshTime: Int) extends AgentId {

  case class On(serverId: AgentId) extends AgentState {
    def changeState(currentTime: Long, message: AnyRef) = message match {
      case Tic() =>
        sendMessages(
          DelayedMessage(Tic(), thisAgent, refreshTime),
          DelayedMessage(LoadLevelRequest(thisAgent), serverId, 0)
        )
      case LoadLevelResponse(loadLevel) => sendMessages(
        DelayedMessage(PostServerLoad(serverId, loadLevel), MonitoringService, 0)
      )
      case TurnOff() => newState(Off())
    }
  }

  case class Off() extends AgentState {
    def changeState(currentTime: Long, message: AnyRef) = message match {
      case TurnOn(serverId) => newState(On(serverId))
    }
  }

}

case class LoadLevelRequest(sender: AgentId)

case class LoadLevelResponse(loadLevel: Int)

case class Tic()

case class TurnOff()

case class TurnOn(serverId: SimpleServer)

case class TaskMessage(sender: AgentId, task: Task)

case class TaskCompleted()

case class ServerFinishedTask(serverId: AgentId)

