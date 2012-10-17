package ru.vasily.simulation

case class DelayedMessage(message: Message, delay: Long)

object DelayedMessage {
  def apply(contents: AnyRef, receiverId: AgentId, delay: Long ): DelayedMessage =
    DelayedMessage(Message(contents, receiverId), delay)
}


