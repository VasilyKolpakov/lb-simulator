package ru.vasily.simulation.core

sealed trait MessageAction

trait MessageTag

case class SendMessage(message: Message, delay: Long, tags: Seq[MessageTag] = Nil) extends MessageAction

object SendMessage {
  def withoutDelay(contents: AnyRef, receiverId: AgentId, tags: Seq[MessageTag] = Nil) =
    new SendMessage(Message(contents, receiverId), 0, tags)
}

case class CancelMessages(tags: Seq[MessageTag]) extends MessageAction

object CancelMessages {
  def apply(tags: MessageTag*): CancelMessages = CancelMessages(tags)
}


