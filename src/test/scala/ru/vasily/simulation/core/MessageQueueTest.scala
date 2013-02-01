package ru.vasily.simulation.core

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class MessageQueueTest extends FlatSpec with ShouldMatchers {
  "A MessageQueue" should "correctly display simple-case emptyness" in {
  }

  it should "correctly display complex-case emptyness" in {
  }

  it should "dequeueOption message having the earliest timestamp" in {
  }

  it should "convert to sequence correctly" in {
  }

  case class DummyAgent(id: Int) extends AgentId

  case class StringTag(tag: String) extends MessageTag

  def message(senderId: Int = 0,
              receiverId: Int = 0,
              message: AnyRef = "",
              tags: Set[String] = Set.empty,
              arrivalTime: Long = 0)
  =
    (MessageWrapper(
      Message(message, DummyAgent(receiverId)),
      DummyAgent(senderId),
      tags.map(StringTag(_))),
      arrivalTime)

  def messageQueue(messages: (MessageWrapper, Long)*) = messages.foldLeft(MessageQueue()) {
    case (queue, (messageWrapper, arrivalTime)) => queue.enqueue(messageWrapper, arrivalTime)
  }
}
