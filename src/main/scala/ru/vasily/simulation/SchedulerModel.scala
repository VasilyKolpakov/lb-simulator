package ru.vasily.simulation

import core.{AgentId, Agent}

trait SchedulerModel {
  def agent(mainServerId: AgentId, nodes: IndexedSeq[AgentId], monitoringService: AgentId): Agent
}

case class FindServerForTask(task: Task)

case class ServerFound(serverId: AgentId, task: Task)

