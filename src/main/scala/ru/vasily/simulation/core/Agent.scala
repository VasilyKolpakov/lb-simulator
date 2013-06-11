package ru.vasily.simulation.core

case class Agent(id: AgentId, initialState: AgentState, messageActions: List[AgentAction] = Nil)

object Agent {
  def apply(id: AgentId, initialState: AgentState, messageActions: AgentAction*): Agent =
    Agent(id, initialState, messageActions.toList)
}
