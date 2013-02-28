package ru.vasily.simulation

import core._
import ru.vasily.simulation.MonitoringService.PostServerLoad

case class MonitoringServiceModel(refreshTime: Option[Int]) {
  def agents(serverIds: Seq[AgentId]): Seq[Agent] = {
    serverIds.map {
      serverId =>
        val agentId = MonitoringAgent(serverId)
        Agent(agentId, agentId.State, agentId ! Tic)
    }
  }

  private case object Tic

  case class MonitoringAgent(serverId: AgentId) extends AgentId {

    case object State extends AgentState {
      def changeState(currentTime: Long, message: AnyRef) = message match {
        case Tic =>
          if (refreshTime.isDefined)
            newActions(
              SendMessage(
                message = Message(Tic, thisAgent),
                delay = refreshTime.get
              ),
              serverId ! LoadLevelRequest(thisAgent)
            )
          else
            noChanges

        case LoadLevelResponse(loadLevel) => newActions(
          MonitoringService ! PostServerLoad(serverId, loadLevel)
        )
      }
    }

  }

}
