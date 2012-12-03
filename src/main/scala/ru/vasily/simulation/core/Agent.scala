package ru.vasily.simulation.core

case class Agent[+I <: AgentId, +S <: AgentState](id: I, initialState: S, messageActions: List[MessageAction] = Nil)

object Agent {
  def apply[I <: AgentId, S <: AgentState](id: I, initialState: S, messageActions: MessageAction*): Agent[I, S] =
    Agent(id, initialState, messageActions.toList)
}
