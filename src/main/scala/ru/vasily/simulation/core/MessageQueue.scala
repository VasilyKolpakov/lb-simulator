package ru.vasily.simulation.core

import ru.vasily.core.PriorityQueue

private case class TimestampedAction(action: MessageQueueAction, actionTime: Long)

private trait MessageQueueAction

private case class MessageWrapper(message: Message, complexTags: Set[MessageTagRecord]) extends MessageQueueAction

private case class ReleaseTags(tagRecords: Set[MessageTagRecord]) extends MessageQueueAction

case class MessageTagRecord(tag: MessageTag, creator: AgentId)


class MessageQueue private(queue: PriorityQueue[TimestampedAction],
                           tagCancellingCount: Map[MessageTagRecord, Int],
                           lastMessageArrivalTime: Long) {

  private def copy(queue: PriorityQueue[TimestampedAction] = queue,
                   tagCancellingCount: Map[MessageTagRecord, Int] = tagCancellingCount,
                   lastMessageArrivalTime: Long = lastMessageArrivalTime) =
    new MessageQueue(queue, tagCancellingCount, lastMessageArrivalTime)

  def dequeueOption: Option[((Long, Message), MessageQueue)] = queue.dequeueOption.flatMap {
    case (head, tail) => head.action match {
      case MessageWrapper(message, tags) => {
        val messageWasCancelled = tags
          .exists(tagCancellingCount.contains(_))
        val messageQueueTail = copy(queue = tail)
        if (messageWasCancelled) {
          messageQueueTail.dequeueOption
        } else {
          Some((head.actionTime, message), messageQueueTail)
        }
      }
      case ReleaseTags(tagRecords) => copy(
        queue = tail,
        tagCancellingCount = tagRecords.foldLeft(tagCancellingCount) {
          case (cancellingCounts, tagRecord) => {
            val count = tagCancellingCount.get(tagRecord)
              .getOrElse(throw new RuntimeException("Tag is already released. Critical internal MessageQueue error"))
            val tagIsExpired = count == 1
            if (tagIsExpired) {
              cancellingCounts - tagRecord
            } else {
              cancellingCounts.updated(tagRecord, count - 1)
            }
          }
        }
      ).dequeueOption
    }
  }

  def timeOfNextEventOption: Option[Long] = queue.dequeueOption.map(_._1.actionTime)

  def enqueue(message: Message, complexTags: Set[MessageTagRecord], messageArrivalTime: Long) =
    copy(
      queue = queue.enqueue(TimestampedAction(MessageWrapper(message, complexTags), messageArrivalTime)),
      lastMessageArrivalTime = math.max(lastMessageArrivalTime, messageArrivalTime)
    )

  def cancelMessages(complexTags: Set[MessageTagRecord]) =
    copy(
      queue = queue.enqueue(TimestampedAction(ReleaseTags(complexTags), lastMessageArrivalTime)),
      tagCancellingCount = complexTags.foldLeft(tagCancellingCount) {
        case (cancellingCounts, tagRecord) =>
          cancellingCounts.updated(tagRecord, 1 + tagCancellingCount.get(tagRecord).getOrElse(0))
      }
    )

  def messagesSeq: Stream[(Long, Message)] = dequeueOption.map {
    case (timeAndMessage, queue) => Stream.cons(timeAndMessage, queue.messagesSeq)
  }.getOrElse(Stream.empty)
}


object MessageQueue {

  private val earliestFirst = new Ordering[TimestampedAction] {
    val reversedLongOrdering: Ordering[Long] = Ordering.Long.reverse

    def compare(x: TimestampedAction, y: TimestampedAction) =
      reversedLongOrdering.compare(x.actionTime, y.actionTime)
  }

  def apply() = new MessageQueue(
    PriorityQueue.apply()(earliestFirst),
    Map(),
    0
  )
}