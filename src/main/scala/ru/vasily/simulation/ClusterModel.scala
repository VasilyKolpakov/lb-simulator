package ru.vasily.simulation

import core.{AgentState, AgentId, Agent}

trait ClusterModel {
  def agents: Seq[Agent]

  def initialMessagesReceiver: AgentId
}
