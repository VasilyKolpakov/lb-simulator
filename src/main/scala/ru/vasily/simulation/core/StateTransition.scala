package ru.vasily.simulation.core

case class StateTransition[+A <: AgentState](newAgentState: A, newMessageActions: Seq[MessageAction] )

