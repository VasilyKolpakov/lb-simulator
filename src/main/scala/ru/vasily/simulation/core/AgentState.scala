package ru.vasily.simulation.core

/*
* Instances of this trait represent agents in agent-based model.
* Any AgentState is immutable, changeState method returns a reaction on event - a new agent state,
* new deferred messages and cancelled messages sent by this agent earlier.
* It is important that agent can cancel only it's own messages)
*
* */
trait AgentState {
  def changeState(currentTime: Long, message: AnyRef): StateTransition[AgentState]

  def newActions(messages: MessageAction*) = newState(this, messages: _*)

  def newState(state: AgentState, actions: MessageAction*) = StateTransition(state, actions)

  def noChanges = StateTransition(this, Nil)
}
