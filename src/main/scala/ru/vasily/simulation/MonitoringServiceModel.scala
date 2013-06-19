package ru.vasily.simulation

import core._

case class MonitoringAgents(agents: Seq[Agent], monitoringServiceId: AgentId)

case class MonitoringServiceModel(refreshTime: Int) {

  def agents(mainServerId: AgentId, serverIds: Seq[AgentId]): MonitoringAgents = {
    val monitoringServiceId = mainServerId.child("MonitoringService")
    val monitoringAgents = serverIds.map {
      serverId =>
        val agentId = serverId.child("MonitoringAgent")
        val initialState = MonitoringAgentStates(agentId, monitoringServiceId, serverId).State
        Agent(agentId, initialState).withInitialActions(agentId ! Tic)
    }
    val serviceAgent = Agent(monitoringServiceId, MonitoringServiceState(Map()))
    val agents = serviceAgent +: monitoringAgents
    MonitoringAgents(agents, monitoringServiceId)
  }

  private case object Tic

  case class MonitoringAgentStates(thisAgentId: AgentId, monitoringServiceId: AgentId, serverId: AgentId) {

    case object State extends AgentState {
      val ticMessageAction = SendMessage(
        message = Message(Tic, thisAgentId),
        delay = refreshTime
      )

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case Tic =>
          newActions(
            ticMessageAction,
            serverId ! LoadLevelRequest(thisAgentId)
          )

        case LoadLevelResponse(loadLevel) => newActions(
          monitoringServiceId ! PostServerLoad(serverId, loadLevel)
        )
      }
    }

  }

}

case class MonitoringServiceState(serversLoad: Map[AgentId, Int]) extends AgentState {
  def changeState(currentTime: Long, message: AnyRef) = message match {
    case PostServerLoad(serverId, load) =>
      val updatedServersLoad = serversLoad.updated(serverId, load)
      newState(copy(updatedServersLoad))
    case GetServersLoad(requester) =>
      newActions(
        requester ! ServersLoad(serversLoad)
      )
  }
}

case class PostServerLoad(serverId: AgentId, load: Int)

case class GetServersLoad(requesterId: AgentId)

case class ServersLoad(serversLoad: Map[AgentId, Int])


case class TaskRecord(task: Task, completionTime: Long)

