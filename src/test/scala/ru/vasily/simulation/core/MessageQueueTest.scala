package ru.vasily.simulation.core

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.OptionValues._
import sun.applet.resources.MsgAppletViewer
import ru.vasily.simulation.core.MessageQueue
import scala.Predef._
import ru.vasily.core.PriorityQueue

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

  it should "cancell more messages" in {

  }

  trait QueueAction {
    def perform(queue: MessageQueue[String, String, Long]): MessageQueue[String, String, Long]

    def perform(queue: TestMessageQueue): TestMessageQueue
  }

  case class Enqueue(msg: String, tags: Set[String], messageArrivalTime: Long) extends QueueAction {
    def perform(queue: MessageQueue[String, String, Long]): MessageQueue[String, String, Long] =
      queue.enqueue(msg, tags, messageArrivalTime)

    def perform(queue: MessageQueueTest): MessageQueueTest.this.type#TestMessageQueue =
      queue.enqueue(msg, tags, messageArrivalTime)
  }


  implicit val messageOrdering = new Ordering[(String, Set[String], Long)] {
    def compare(x: (String, Set[String], Long), y: (String, Set[String], Long)): Int =
      -Ordering.Long.compare(x._3, y._3)
  }

  class TestMessageQueue(queue: PriorityQueue[(String, Set[String], Long)] = PriorityQueue()) {
    def enqueue(message: String, complexTags: Set[String], messageArrivalTime: Long) =
      new TestMessageQueue(queue)

    def cancelMessages(tags: Set[String]) = {
      val messages = queue.toSeq.filterNot {
        case (_, messageTags, _) => tags.exists(messageTags.contains(_))
      }
      new TestMessageQueue(PriorityQueue(messages: _*))
    }
  }


  def message(msg: String = "",
              tags: Set[String] = Set.empty,
              arrivalTime: Long = 0)
  = (msg, tags, arrivalTime)

  def messageQueue(messages: (String, Set[String], Long)*) = messages.foldLeft(MessageQueue[String, String, Long](0)) {
    case (queue, (message, tags, arrivalTime)) => queue.enqueue(message, tags, arrivalTime)
  }

}
