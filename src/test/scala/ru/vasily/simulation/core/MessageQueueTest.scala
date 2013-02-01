package ru.vasily.simulation.core

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.OptionValues._

class MessageQueueTest extends FlatSpec with ShouldMatchers {
  "A MessageQueue" should "dequeue message having the earliest timestamp" in {
    messageQueue(
      message(arrivalTime = 100),
      message(arrivalTime = 20),
      message(msg = "the msg", arrivalTime = 10)
    ).dequeueOption.value._1._1 should equal(10)
  }

  it should "cancell messages" in {
    messageQueue(
      message(arrivalTime = 100),
      message(arrivalTime = 20),
      message(msg = "the msg", arrivalTime = 10, tags = Set("tag"))
    ).cancelMessages(Set("tag"))
      .dequeueOption.value._1._1 should equal(20)
  }

  def message(msg: String = "",
              tags: Set[String] = Set.empty,
              arrivalTime: Long = 0)
  = (msg, tags, arrivalTime)

  def messageQueue(messages: (String, Set[String], Long)*) = messages.foldLeft(MessageQueue[String, String, Long](0)) {
    case (queue, (message, tags, arrivalTime)) => queue.enqueue(message, tags, arrivalTime)
  }


}
