package ru.vasily.simulation

import core._

object MonitoringService extends AgentId {

  val agent = Agent(MonitoringService, State(Map()))

  case class State(serversLoad: Map[AgentId, Int]) extends AgentState {
    def changeState(currentTime: Long, message: AnyRef) = message match {
      case PostServerLoad(serverId, load) =>
        newState(State(serversLoad.updated(serverId, load)))
      case GetServersLoad(requester) =>
        newActions(
            requester ! ServersLoad(serversLoad)
        )
    }
  }

  case class PostServerLoad(serverId: AgentId, load: Int)

  case class GetServersLoad(requesterId: AgentId)

  case class ServersLoad(serversLoad: Map[AgentId, Int])

  override def toString = "MonitoringService"
}

case class TaskRecord(task: Task, completionTime: Long)

