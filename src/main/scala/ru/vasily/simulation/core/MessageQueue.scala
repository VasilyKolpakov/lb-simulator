package ru.vasily.simulation.core

import ru.vasily.core.{Orderings, PriorityQueue}

private case class TimestampedAction[Msg, Tag, Time](action: MessageQueueAction[Msg, Tag], actionTime: Time)

private trait MessageQueueAction[Msg, Tag]

private case class MessageWrapper[Msg, Tag](message: Msg, tags: Set[Tag]) extends MessageQueueAction[Msg, Tag]

private case class ReleaseTags[Msg, Tag](tagRecords: Set[Tag]) extends MessageQueueAction[Msg, Tag]

case class MessageTagRecord(tag: MessageTag, creator: AgentId)


class MessageQueue[Msg, Tag, Time] private(queue: PriorityQueue[TimestampedAction[Msg, Tag, Time]],
                                           tagCancellingCount: Map[Tag, Int],
                                           lastMessageArrivalTime: Time,
                                           timeOrdering: Ordering[Time]) {

  private def copy(queue: PriorityQueue[TimestampedAction[Msg, Tag, Time]] = queue,
                   tagCancellingCount: Map[Tag, Int] = tagCancellingCount,
                   lastMessageArrivalTime: Time = lastMessageArrivalTime,
                   timeOrdering: Ordering[Time] = timeOrdering) =
    new MessageQueue(queue, tagCancellingCount, lastMessageArrivalTime, timeOrdering)

  def dequeueOption: Option[((Time, Msg), MessageQueue[Msg, Tag, Time])] = queue.dequeueOption.flatMap {
    case (head, tail) => head.action match {
      case MessageWrapper(message: Msg, tags: Set[Tag]) => {
        val messageWasCancelled = tags
          .exists(tagCancellingCount.contains(_))
        val messageQueueTail = copy(queue = tail)
        if (messageWasCancelled) {
          messageQueueTail.dequeueOption
        } else {
          Some((head.actionTime, message), messageQueueTail)
        }
      }
      case ReleaseTags(tag) => copy(
        queue = tail,
        tagCancellingCount = tag.foldLeft(tagCancellingCount) {
          case (cancellingCounts, tag) => {
            val count = tagCancellingCount.get(tag)
              .getOrElse(throw new RuntimeException("Tag is already released. Critical internal MessageQueue error"))
            val tagIsExpired = count == 1
            if (tagIsExpired) {
              cancellingCounts - tag
            } else {
              cancellingCounts.updated(tag, count - 1)
            }
          }
        }
      ).dequeueOption
    }
  }

  def timeOfNextEventOption: Option[Time] = queue.dequeueOption.map(_._1.actionTime)

  def enqueue(message: Msg, complexTags: Set[Tag], messageArrivalTime: Time) =
    copy(
      queue = queue.enqueue(TimestampedAction[Msg, Tag, Time](MessageWrapper(message, complexTags), messageArrivalTime)),
      lastMessageArrivalTime = timeOrdering.max(lastMessageArrivalTime, messageArrivalTime)
    )

  def cancelMessages(complexTags: Set[Tag]) =
    copy(
      queue = queue.enqueue(TimestampedAction[Msg, Tag, Time](ReleaseTags(complexTags), lastMessageArrivalTime)),
      tagCancellingCount = complexTags.foldLeft(tagCancellingCount) {
        case (cancellingCounts, tagRecord) =>
          cancellingCounts.updated(tagRecord, 1 + tagCancellingCount.get(tagRecord).getOrElse(0))
      }
    )

  def messagesSeq: Stream[(Time, Msg)] = dequeueOption.map {
    case (timeAndMessage, queue) => Stream.cons(timeAndMessage, queue.messagesSeq)
  }.getOrElse(Stream.empty)
}


object MessageQueue {

  private def earliestFirst[Msg, Tag, Time](timeOrdering: Ordering[Time]) = new Ordering[TimestampedAction[Msg, Tag, Time]] {
    val reversedTimeOrdering: Ordering[Time] = timeOrdering.reverse

    def compare(x: TimestampedAction[Msg, Tag, Time], y: TimestampedAction[Msg, Tag, Time]) =
      reversedTimeOrdering.compare(x.actionTime, y.actionTime)
  }

  def apply[Msg, Tag, Time](startTime: Time)(implicit timeOrdering: Ordering[Time]) = new MessageQueue[Msg, Tag, Time](
    PriorityQueue.apply()(earliestFirst[Msg, Tag, Time](timeOrdering)),
    Map(),
    startTime,
    timeOrdering
  )
}