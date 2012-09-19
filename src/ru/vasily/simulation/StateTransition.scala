package ru.vasily.simulation

import collection.immutable

case class StateTransition[+A <: AgentState](newAgentState: A, newMessages: Seq[DelayedMessage] = Seq.empty)

object StateTransition {
  def apply[A <: AgentState](newAgentState: A, newMessage: DelayedMessage, otherNewMessages: DelayedMessage*): StateTransition[A] =
    StateTransition(newAgentState, newMessage :: immutable.List(otherNewMessages: _*))
}