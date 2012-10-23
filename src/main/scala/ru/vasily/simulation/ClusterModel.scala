package ru.vasily.simulation

trait ClusterModel {
  def agents: Seq[Agent[AgentId, AgentState]]

  def initialMessagesReceiver: AgentId
}
