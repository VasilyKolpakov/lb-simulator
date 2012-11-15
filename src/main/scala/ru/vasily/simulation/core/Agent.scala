package ru.vasily.simulation.core

case class Agent[+I <: AgentId, +S <: AgentState](id: I, initialState: S, initialMessages: List[DelayedMessage] = Nil)

object Agent {
  def apply[I <: AgentId, S <: AgentState](id: I, initialState: S, initialMessages: DelayedMessage*): Agent[I, S] =
    Agent(id, initialState, initialMessages.toList)
}
