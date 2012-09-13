package ru.vasily.simulation

/*
* Instances of this trait represent agents in agent-based model.
* Any AgentState is immutable, changeState method returns a reaction on event - a new agent state,
* new deferred messages
* todo:(and cancelled messages sent by this agent earlier. It is important that agent can cancel only it's own messages)
*
* */
trait AgentState {
  def changeState(message: DeferredMessage): StateTransition[AgentState]
}
