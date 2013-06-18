package ru.vasily.simulation.core

case class Agent(id: AgentId, initialState: AgentState, messageActions: Seq[AgentAction] = Nil) {
  def withInitialActions(initialAgentActions: AgentAction*) = copy(messageActions = initialAgentActions)
}
