package ru.vasily.simulation

import core.{AgentState, Agent, AgentId}

object RoundRobinSchedulerModel extends SchedulerModel {

  def agent(mainServerId: AgentId, nodes: IndexedSeq[AgentId]): SchedulerAgents = {
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
    val schedulerId = mainServerId.child("RoundRobinScheduler")
    val agent = Agent(schedulerId, State())
    SchedulerAgents(Seq(agent), agent.id)
  }

}
