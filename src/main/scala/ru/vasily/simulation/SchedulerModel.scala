package ru.vasily.simulation

import core.{AgentId, Agent}

trait SchedulerModel {
  def agent(mainServerId: AgentId, nodes: IndexedSeq[AgentId]): SchedulerAgents
}

case class SchedulerAgents(agents: Seq[Agent], mainAgentId: AgentId)

case class FindServerForTask(task: Task)

case class ServerFound(serverId: AgentId, task: Task)

