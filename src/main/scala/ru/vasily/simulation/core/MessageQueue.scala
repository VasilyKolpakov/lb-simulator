package ru.vasily.simulation.core

import ru.vasily.core.PriorityQueue

private case class TimestampedAction(action: MessageQueueAction, actionTime: Long)

private trait MessageQueueAction

private case class MessageWrapper(message: Message) extends MessageQueueAction

private case class ReleaseTags(tags: Seq[MessageTag]) extends MessageQueueAction

private case class MessageTagRecord(tag: MessageTag, creator: AgentId)


class MessageQueue private(queue: PriorityQueue[TimestampedAction],
                           tagCancellingCount: Map[MessageTagRecord, Int],
                           lastMessageArrivalTime: Map[AgentId, Long],
                           val currentTime: Long) {

  def dequeueOption: Option[((Long, Message), MessageQueue)] = sys.error("not implemented")

  def timeOfNextEventOption: Option[Long] = sys.error("not implemented")

  def enqueue(action: MessageAction, agent: AgentId) = action match {
    case SendMessage(msg, delay, tags) => {
      val updatedQueue = queue.enqueue(TimestampedAction(msg, delay + currentTime))
      val
      new MessageQueue(
        queue = queue.enqueue(TimestampedAction(msg, delay + currentTime)),
        updatedTagCancellingCount,
        updatedLastMessageArrivalTime,
        updatedCurrentTime)
    }
    case CancelMessages(tags) => {
      tags.foldLeft(tagsTimeIntervals) {
        case (tagsIntervals, tag) =>
          val currentIntervalOption =
            for {interval <- tagsTimeIntervals.get(MessageTagRecord(tag, agent))
                 if interval.end > currentTime
            } yield TimeInterval()


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