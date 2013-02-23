package ru.vasily.simulation.core

sealed trait MessageAction

trait MessageTag

case class SendMessage(message: Message, delay: Long, tags: Set[MessageTag] = Set.empty) extends MessageAction

object SendMessage {
  def withoutDelay(contents: AnyRef, receiverId: AgentId, tags: Set[MessageTag] = Set.empty) =
    new SendMessage(Message(contents, receiverId), 0, tags)
}

case class CancelMessages(tags: Set[MessageTag]) extends MessageAction

object CancelMessages {
  def apply(tags: MessageTag*): CancelMessages = CancelMessages(tags.toSet)
}


