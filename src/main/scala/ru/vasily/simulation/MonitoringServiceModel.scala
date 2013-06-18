package ru.vasily.simulation

import core._
import ru.vasily.simulation.MonitoringService.PostServerLoad

case class MonitoringAgents(agents: Seq[Agent], monitoringServiceId: AgentId)

case class MonitoringServiceModel(refreshTime: Int) {
  def agents(serverIds: Seq[AgentId]): MonitoringAgents = {
    val agents = MonitoringService.agent +: serverIds.map {
      serverId =>
        val agentId = MonitoringAgent(serverId)
        Agent(agentId, agentId.State).withInitialActions(agentId ! Tic)
    }
    MonitoringAgents(agents, MonitoringService.agent.id)
  }

  private case object Tic

  case class MonitoringAgent(serverId: AgentId) extends AgentId {

    case object State extends AgentState {
      val ticMessageAction = SendMessage(
        message = Message(Tic, thisAgent),
        delay = refreshTime
      )

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case Tic =>
          newActions(
            ticMessageAction,
            serverId ! LoadLevelRequest(thisAgent)
          )

        case LoadLevelResponse(loadLevel) => newActions(
          MonitoringService ! PostServerLoad(serverId, loadLevel)
        )
      }
    }

  }

}
