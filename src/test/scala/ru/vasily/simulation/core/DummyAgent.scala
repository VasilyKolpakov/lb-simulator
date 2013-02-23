package ru.vasily.simulation.core

case object DummyAgent extends AgentId
case object DummyState extends AgentState {
  def changeState(currentTime: Long, message: AnyRef) = noChanges
}
