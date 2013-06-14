package ru.vasily.simulation

import core.{AgentId, Agent}

trait ClusterModel {
  def agents(masterId: AgentId): ClusterAgents
}

case class ClusterAgents(agents: Seq[Agent], initialMessagesReceiver: AgentId)
