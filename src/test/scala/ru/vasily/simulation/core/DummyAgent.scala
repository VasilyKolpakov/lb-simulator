package ru.vasily.simulation.core

case object DummyAgentState extends AgentState {
  def changeState(currentTime: Long, message: AnyRef) = noChanges
}
