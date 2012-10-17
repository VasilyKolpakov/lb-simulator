package ru.vasily.simulation

trait ClusterModel {
  def agents: Seq[(AgentId, AgentState)]

  def initialMessagesReceiver: AgentId
}
