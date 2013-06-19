package ru.vasily.simulation

import collection.immutable.Queue
import core._

case class SimpleServerStates(thisServerId: AgentId, serverPerformance: Double) {

  private case class TaskCompleted()

  def taskCompletedMessage(task: Task) = {
    val executionTime: Long = (task.executionTime / serverPerformance).toInt
    SendMessage(
      message = Message(TaskCompleted(), thisServerId),
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
      val callbackMessage = SendMessage.withoutDelay(TaskFinished(thisServerId, completedTask), messageSender)
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

  def generateServers(serversPerformance: Seq[Double], serversRootId: AgentId): Seq[Agent] =
    serversPerformance.zipWithIndex.map {
      case (performance, index) => {
        val agentId = serversRootId.child(s"SimpleServer#$index")
        val states: SimpleServerStates = SimpleServerStates(agentId, performance)
        Agent(agentId, states.IdleState()).withInitialActions(LogAction(ServerInfo(performance)))
      }
    }
}

case class LoadLevelRequest(requester: AgentId)

case class LoadLevelResponse(loadLevel: Int)

case class TaskMessage(sender: AgentId, task: Task)


case class TaskFinished(executorId: AgentId, task: Task) {
  def forward(receiverId: AgentId) = SendMessage.withoutDelay(TaskFinished(executorId, task), receiverId)
}

