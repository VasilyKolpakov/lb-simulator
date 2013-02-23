package ru.vasily.simulation.core

case class Agent(id: AgentId, initialState: AgentState, messageActions: List[MessageAction] = Nil)

object Agent {
  def apply(id: AgentId, initialState: AgentState, messageActions: MessageAction*): Agent =
    Agent(id, initialState, messageActions.toList)
}
