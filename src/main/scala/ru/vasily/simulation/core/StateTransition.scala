package ru.vasily.simulation.core

case class StateTransition[+A <: AgentState](newAgentState: A, newMessageActions: Seq[MessageAction]) {
  def addActions(actions: MessageAction*) = copy(newMessageActions = actions ++ newMessageActions)
}

