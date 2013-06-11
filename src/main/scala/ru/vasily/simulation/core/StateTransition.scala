package ru.vasily.simulation.core

case class StateTransition[+A <: AgentState](newAgentState: A, newMessageActions: Seq[AgentAction]) {
  def addActions(actions: AgentAction*) = copy(newMessageActions = actions ++ newMessageActions)
}

