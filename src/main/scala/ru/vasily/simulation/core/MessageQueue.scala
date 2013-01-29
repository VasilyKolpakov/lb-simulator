package ru.vasily.simulation.core

import ru.vasily.core.PriorityQueue

private case class TimestampedAction(action: MessageAction,
                                     actionTime: Long,
                                     creationTime: Long,
                                     creator: AgentId)

private case class MessageTagRecord(tag: MessageTag, creator: AgentId)


private case class TimeInterval(val start: Long, val end: Long)

class MessageQueue private(queue: PriorityQueue[TimestampedAction],
                           tagsTimeIntervals: Map[MessageTagRecord, TimeInterval],
                           lastMessageArrivalTime: Map[AgentId, Long],
                           val currentTime: Long) {

  def dequeueOption: Option[((Long, Message), MessageQueue)] = sys.error("not implemented")

  def timeOfNextEventOption: Option[Long] = sys.error("not implemented")

  def enqueue(action: MessageAction, agent: AgentId) = action match {
    case SendMessage(msg, delay, tags) => {

    }
    case CancelMessages(tags) => {
      tags.foldLeft(tagsTimeIntervals) {
        case (tagsIntervals, tag) =>
          val updatedTimeInterval =
            tagsTimeIntervals.get(TimeInterval(tag, agent))

      }
    }
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