package ru.vasily.simulation.core

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.OptionValues._
import scala.Predef._
import ru.vasily.core.PriorityQueue
import util.Random

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
    val numberOfActions = 10000
  }

  def randomQueueActions(numberOfActions: Int) = {
    val rnd = new Random(0)
    def randomTags = Set(List.fill(rnd.nextInt(5)(rnd.nextInt(10).toString)): _*)
    def randomAction = {
      val randomDouble = rnd.nextDouble()
      if (randomDouble < 0.45) {
        val tags = randomTags
        Enqueue("msg", tags, rnd.nextLong())
      } else if (randomDouble < 0.9) {
        Dequeue()
      } else {
        CancelMessages(randomTags)
      }
    }
    List.fill(numberOfActions)(randomAction)
  }

  trait QueueAction {
    // returns (possibly dequeued (time, message), updated queue)
    def perform(queue: TaggedMessageQueue): (Option[(Long, String)], TaggedMessageQueue)
  }

  case class Enqueue(msg: String, tags: Set[String], messageArrivalTime: Long) extends QueueAction {
    def perform(queue: TaggedMessageQueue) = (None, queue.enqueue(msg, tags, messageArrivalTime))
  }

  case class Dequeue() extends QueueAction {
    // returns (possibly dequeued (time, message), updated queue)
    def perform(queue: TaggedMessageQueue) = queue.dequeueOption.map {
      case (timeAndMessage, queueTail) => (Some(timeAndMessage), queueTail)
    }.getOrElse(None, queue)
  }

  case class CancelMessages(tags: Set[String]) extends QueueAction {
    // returns (possibly dequeued (time, message), updated queue)
    def perform(queue: TaggedMessageQueue) = (None, queue.cancelMessages(tags))
  }

  implicit val messageOrdering = new Ordering[(String, Set[String], Long)] {
    def compare(x: (String, Set[String], Long), y: (String, Set[String], Long)): Int =
      -Ordering.Long.compare(x._3, y._3)
  }

  trait TaggedMessageQueue {
    def enqueue(msg: String, tags: Set[String], messageArrivalTime: Long): TaggedMessageQueue

    def dequeueOption: Option[((Long, String), TaggedMessageQueue)]

    def cancelMessages(tags: Set[String]): TaggedMessageQueue
  }

  case class MessageTaggedMessageQueue(queue: MessageQueue[String, String, Long])
    extends TaggedMessageQueue {
    def enqueue(msg: String, tags: Set[String], messageArrivalTime: Long) =
      MessageTaggedMessageQueue(queue.enqueue(msg, tags, messageArrivalTime))

    def dequeueOption = queue.dequeueOption.map {
      case (timeAndMessage, queueTail) => (timeAndMessage, MessageTaggedMessageQueue(queueTail))
    }

    def cancelMessages(tags: Set[String]) =
      MessageTaggedMessageQueue(queue.cancelMessages(tags))
  }

  case class TestMessageTaggedMessageQueue(queue: PriorityQueue[(String, Set[String], Long)] = PriorityQueue())
    extends TaggedMessageQueue {
    def enqueue(message: String, complexTags: Set[String], messageArrivalTime: Long) =
      new TestMessageTaggedMessageQueue(queue)

    def dequeueOption = queue.dequeueOption.map {
      case ((message, _, time), queueTail) => ((time, message), new TestMessageTaggedMessageQueue(queueTail))
    }

    def cancelMessages(tags: Set[String]) = {
      val messages = queue.toSeq.filterNot {
        case (_, messageTags, _) => tags.exists(messageTags.contains(_))
      }
      new TestMessageTaggedMessageQueue(PriorityQueue(messages: _*))
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
