package ru.vasily.simulation

import collection.immutable

case class StateTransition[+A <: AgentState](newAgentState: A, newMessages: Seq[DeferredMessage] = Seq.empty)

object StateTransition {
  def apply[A <: AgentState](newAgentState: A, newMessage: DeferredMessage, otherNewMessages: DeferredMessage*): StateTransition[A] =
    StateTransition(newAgentState, newMessage :: immutable.List(otherNewMessages: _*))
}