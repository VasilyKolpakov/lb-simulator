package ru.vasily.simulation.core

import ru.vasily.core.PriorityQueue

private case class TimestampedAction(action: MessageQueueAction, actionTime: Long)

trait MessageQueueAction

case class MessageWrapper(message: Message, agent: AgentId, tags: Set[MessageTag]) extends MessageQueueAction

private case class ReleaseTags(tagRecords: Set[MessageTagRecord]) extends MessageQueueAction

private case class MessageTagRecord(tag: MessageTag, creator: AgentId)


class MessageQueue private(queue: PriorityQueue[TimestampedAction],
                           tagCancellingCount: Map[MessageTagRecord, Int],
                           lastMessageArrivalTime: Long) {

  private def copy(queue: PriorityQueue[TimestampedAction] = queue,
                   tagCancellingCount: Map[MessageTagRecord, Int] = tagCancellingCount,
                   lastMessageArrivalTime: Long = lastMessageArrivalTime) =
    new MessageQueue(queue, tagCancellingCount, lastMessageArrivalTime)

  def dequeueOption: Option[((Long, Message), MessageQueue)] = queue.dequeueOption.flatMap {
    case (head, tail) => head.action match {
      case MessageWrapper(message, agent, tags) => {
        val messageWasCancelled = tags
          .map(MessageTagRecord(_, agent))
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

  def enqueue(messageWrapper: MessageWrapper, messageArrivalTime: Long) =
    copy(
      queue = queue.enqueue(TimestampedAction(messageWrapper, messageArrivalTime)),
      lastMessageArrivalTime = math.max(lastMessageArrivalTime, messageArrivalTime)
    )

  def cancelMessages(tags: Set[MessageTag], agent: AgentId) = {
    val tagRecords = tags.map(MessageTagRecord(_, agent))
    copy(
      queue = queue.enqueue(TimestampedAction(ReleaseTags(tagRecords), lastMessageArrivalTime)),
      tagCancellingCount = tagRecords.foldLeft(tagCancellingCount) {
        case (cancellingCounts, tagRecord) =>
          cancellingCounts.updated(tagRecord, 1 + tagCancellingCount.get(tagRecord).getOrElse(0))
      }
    )
  }

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