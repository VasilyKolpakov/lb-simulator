package ru.vasily.simulation.core

sealed trait AgentAction

trait MessageTag

case class SendMessage(message: Message, delay: Long, tags: Set[MessageTag] = Set.empty) extends AgentAction {
  def withDelay(delayVal: Long) = copy(delay = delayVal)

  def withTags(tagsVal: Set[MessageTag]) = copy(tags = tagsVal)
}

object SendMessage {
  def withoutDelay(contents: AnyRef, receiverId: AgentId, tags: Set[MessageTag] = Set.empty) =
    new SendMessage(Message(contents, receiverId), 0, tags)
}

case class CancelMessages(tags: Set[MessageTag]) extends AgentAction

object CancelMessages {
  def apply(tags: MessageTag*): CancelMessages = CancelMessages(tags.toSet)
}

case class LogAction(record:Any) extends AgentAction

