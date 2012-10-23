package ru.vasily.simulation

object MonitoringService extends AgentId {

  val agent = Agent(MonitoringService, State(Map(), Map()))

  case class State(serversHistory: Map[AgentId, Seq[TaskRecord]], serversLoad: Map[AgentId, Int]) extends AgentState {
    def changeState(currentTime: Long, message: AnyRef) = message match {
      case Report(serverId, taskRecord) => {
        val taskRecords = taskRecord +: serversHistory.get(serverId).getOrElse(Nil)
        val history = serversHistory.updated(serverId, taskRecords)
        newState(State(history, serversLoad))
      }
      case PostServerLoad(serverId, load) =>
        newState(State(serversHistory, serversLoad.updated(serverId, load)))
      case GetServersLoad(requester) => sendMessages(
        DelayedMessage(ServersLoad(serversLoad), requester, 0)
      )
    }
  }

  case class Report(serverId: AgentId, taskRecord: TaskRecord)

  case class PostServerLoad(serverId: AgentId, load: Int)

  case class GetServersLoad(requesterId: AgentId)

  case class ServersLoad(serversLoad: Map[AgentId, Int])


  override def toString = "MonitoringService"
}

case class TaskRecord(task: Task, completionTime: Long)

