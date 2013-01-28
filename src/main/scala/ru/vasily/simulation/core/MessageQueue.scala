package ru.vasily.simulation.core

import ru.vasily.core.PriorityQueue

private case class TimestampedAction(action: MessageAction,
                                     actionTime: Long,
                                     creationTime: Long,
                                     creator: AgentId)

private case class MessageTagRecord(creator: AgentId, tag: MessageTag)

private case class

private class TimeInterval(val start: Long, val end: Long)

class MessageQueue private(queue: PriorityQueue[TimestampedAction],
                           tagsMap: Map[MessageTagRecord, TimeInterval],
                           lastMessageArrivalTime: Map[AgentId, Long],
                           val currentTime: Long) {

  def dequeueOption: Option[((Long, Message), MessageQueue)] = sys.error("not implemented")

  def timeOfNextEventOption: Option[Long] = sys.error("not implemented")

  def enqueue(action: MessageAction) = action match {
    case SendMessage(msg, delay, tags) => {

    }
    case CancelMessages(tags) =>
  }

  def ++(timestampedMessages: Seq[MessageAction]): MessageQueue = {
  }

  def messagesSeq: Seq[(Long, Message)] = sys.error("not implemented")
}


object MessageQueue {

  private val earliestFirst = new Ordering[TimestampedAction] {
    val reversedLongOrdering: Ordering[Long] = Ordering.Long.reverse

    def compare(x: TimestampedAction, y: TimestampedAction) =
      reversedLongOrdering.compare(x.actionTime, y.actionTime)
  }

  def apply[M](initialActions: MessageAction*) = new MessageQueue(
    PriorityQueue.apply()(earliestFirst),
    Map(),
    Map(),
    0
  ) ++ initialActions
}