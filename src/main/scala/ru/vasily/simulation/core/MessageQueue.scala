package ru.vasily.simulation.core

import ru.vasily.core.{Orderings, PriorityQueue}

private case class TimestampedMessage[Msg, Tag, Time](message: Msg, tags: Set[Tag], timestamp: Time, orderNumber: Long)


case class MessageTagRecord(tag: MessageTag, creator: AgentId)


class MessageQueue[Msg, Tag, Time] private(queue: PriorityQueue[TimestampedMessage[Msg, Tag, Time]],
                                           tagToMessagesMap: Map[Tag, Set[Long]],
                                           notCancelledMessages: Set[Long],
                                           numberOfEnqueuedMessages: Long,
                                           timeOrdering: Ordering[Time]) {

  private def copy(queue: PriorityQueue[TimestampedMessage[Msg, Tag, Time]] = queue,
                   tagToMessagesMap: Map[Tag, Set[Long]] = tagToMessagesMap,
                   notCancelledMessages: Set[Long] = notCancelledMessages,
                   numberOfEnqueuedMessages: Long = numberOfEnqueuedMessages) =
    new MessageQueue(queue, tagToMessagesMap, notCancelledMessages, numberOfEnqueuedMessages, timeOrdering)

  def dequeueOption: Option[((Time, Msg), MessageQueue[Msg, Tag, Time])] = queue.dequeueOption.flatMap {
    headAndTail =>
      val (head, tail) = headAndTail
      val messageOrderNumber: Long = head.orderNumber
      val messageWasNotCancelled = notCancelledMessages.contains(messageOrderNumber)
      val updatedNotCancelledMessages = notCancelledMessages - messageOrderNumber
      val updatedTagToMessageMap = head.tags.foldLeft(tagToMessagesMap) {
        (tagMap, tag) => {
          val messagesWithGivenTag = tagMap.get(tag).map(_ - messageOrderNumber).getOrElse(Set())
          if (messagesWithGivenTag.isEmpty) {
            tagMap - tag
          } else {
            tagMap.updated(tag, messagesWithGivenTag)
          }
        }
      }
      val updatedQueue = copy(
        queue = tail,
        tagToMessagesMap = updatedTagToMessageMap,
        notCancelledMessages = updatedNotCancelledMessages
      )
      if (messageWasNotCancelled) {
        Some((head.timestamp, head.message), updatedQueue)
      } else {
        updatedQueue.dequeueOption
      }

  }


  def timeOfNextEventOption: Option[Time] = dequeueOption.map(_._1._1)

  def enqueue(message: Msg, tags: Set[Tag], messageArrivalTime: Time) = {
    val messageOrderNumber = numberOfEnqueuedMessages
    val updatedNotCancelledMessages = notCancelledMessages + messageOrderNumber
    val updatedTagToMessagesMap = tags.foldLeft(tagToMessagesMap) {
      (tagMap, tag) =>
        tagToMessagesMap.updated(
          tag,
          tagToMessagesMap
            .get(tag).map(_ + messageOrderNumber)
            .getOrElse(Set(messageOrderNumber)))
    }
    copy(
      queue = queue.enqueue(TimestampedMessage[Msg, Tag, Time](message, tags, messageArrivalTime, numberOfEnqueuedMessages)),
      tagToMessagesMap = updatedTagToMessagesMap,
      notCancelledMessages = updatedNotCancelledMessages,
      numberOfEnqueuedMessages = numberOfEnqueuedMessages + 1
    )
  }

  def cancelMessages(tags: Set[Tag]) = copy(
    notCancelledMessages = notCancelledMessages -- tags.map(tagToMessagesMap.getOrElse(_, Set())).flatten.toSet
  )

  def messagesSeq: Stream[(Time, Msg)] = dequeueOption.map {
    case (timeAndMessage, queue) => Stream.cons(timeAndMessage, queue.messagesSeq)
  }.getOrElse(Stream.empty)
}


object MessageQueue {

  private def earliestFirst[Msg, Tag, Time](timeOrdering: Ordering[Time]) = new Ordering[TimestampedMessage[Msg, Tag, Time]] {
    val reversedTimeOrdering: Ordering[Time] = timeOrdering.reverse

    def compare(x: TimestampedMessage[Msg, Tag, Time], y: TimestampedMessage[Msg, Tag, Time]) =
      reversedTimeOrdering.compare(x.timestamp, y.timestamp)
  }

  def apply[Msg, Tag, Time](startTime: Time)(implicit timeOrdering: Ordering[Time]) = new MessageQueue[Msg, Tag, Time](
    PriorityQueue.apply()(earliestFirst[Msg, Tag, Time](timeOrdering)),
    Map(),
    Set(),
    0,
    timeOrdering
  )
}