package ru.vasily.simulation.core

case class DelayedMessage(message: Message, delay: Long)

object DelayedMessage {
  def apply(contents: AnyRef, receiverId: AgentId, delay: Long = 0): DelayedMessage =
    DelayedMessage(Message(contents, receiverId), delay)
}


