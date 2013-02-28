package ru.vasily.simulation

import core.{AgentState, Agent, AgentId}

object RoundRobinSchedulerModel extends SchedulerModel {

  def agent(mainServerId: AgentId, nodes: IndexedSeq[AgentId], monitoringService: AgentId): Agent = {
    case class State(currentServerIndex: Int = 0) extends AgentState {
      val numberOfServers = nodes.size

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case FindServerForTask(task) => newState(
          State((currentServerIndex + 1) % numberOfServers),
          mainServerId ! ServerFound(nodes(currentServerIndex), task)
        )
        case _: TaskFinished => noChanges
      }
    }
    Agent(RoundRobinScheduler(mainServerId), State())
  }

  private case class RoundRobinScheduler(mainServerId: AgentId) extends AgentId

}
