package ru.vasily.simulation.core

/*
* Instances of this trait represent agents in agent-based model.
* Any AgentState is immutable, changeState method returns a reaction on event - a new agent state,
* new deferred messages
* todo:(and cancelled messages sent by this agent earlier. It is important that agent can cancel only it's own messages)
*
* */
trait AgentState {
  def changeState(currentTime: Long, message: AnyRef): StateTransition[AgentState]

  def sendMessages(messages: DelayedMessage*) = StateTransition(this, messages)

  def newState(state: AgentState, messages: DelayedMessage*) = StateTransition(state, messages)

  def doNothing = StateTransition(this)
}
