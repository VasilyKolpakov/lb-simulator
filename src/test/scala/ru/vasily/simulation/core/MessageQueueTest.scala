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

  it should "cancel messages" in {
    messageQueue(
      message(arrivalTime = 100),
      message(arrivalTime = 20),
      message(msg = "the msg", arrivalTime = 10, tags = Set("tag"))
    ).cancelMessages(Set("tag"))
      .dequeueOption.value._1._1 should equal(20)
  }

  it should "cancel more messages" in {
    val actions = randomQueueActions(numberOfActions = 10000)
    val expectedDequeuedMessages = applyActions(TestTaggedMessageQueue(), actions)._1
    val actual = applyActions(ActualTaggedMessageQueue(), actions)._1
    actual should equal(expectedDequeuedMessages)
  }


  it should "not have memory leaks" in {
    val actions = randomQueueActions(numberOfActions = 10000) ++ Seq.fill(10000)(Dequeue())
    val queueWrapper = applyActions(ActualTaggedMessageQueue(), actions)._2
    // queue can contain canceled messages and be empty
    val Some((_, queue)) = queueWrapper.queue.enqueue("", Set(), Integer.MAX_VALUE).dequeueOption
    queue.isCompletelyEmpty should equal(true)
  }

  def applyActions[T <: TaggedMessageQueue[T]](queue: T, actions: Seq[QueueAction]): (Seq[(Int, String)], T) =
    actions.foldLeft(List[(Int, String)](), queue) {
      case ((dequeuedMessages, queue), action) => {
        val (messageOption, updatedQueue) = action.perform(queue)
        (messageOption.map(_ :: dequeuedMessages).getOrElse(dequeuedMessages), updatedQueue)
      }
    }

  def randomQueueActions(numberOfActions: Int) = {
    val rnd = new Random(0)
    def randomTags = Set(List.fill(rnd.nextInt(5))((rnd.nextInt(10).toString)): _*)
    def randomAction = {
      val randomDouble = rnd.nextDouble()
      if (randomDouble < 0.45) {
        val tags = randomTags
        Enqueue("msg", tags, rnd.nextInt())
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
    def perform[T <: TaggedMessageQueue[T]](queue: T): (Option[(Int, String)], T)
  }

  case class Enqueue(msg: String, tags: Set[String], messageArrivalTime: Int) extends QueueAction {
    def perform[T <: TaggedMessageQueue[T]](queue: T) = (None, queue.enqueue(msg, tags, messageArrivalTime))
  }

  case class Dequeue() extends QueueAction {
    // returns (possibly dequeued (time, message), updated queue)
    def perform[T <: TaggedMessageQueue[T]](queue: T) = queue.dequeueOption.map {
      case (timeAndMessage, queueTail) => (Some(timeAndMessage), queueTail)
    }.getOrElse(None, queue)
  }

  case class CancelMessages(tags: Set[String]) extends QueueAction {
    // returns (possibly dequeued (time, message), updated queue)
    def perform[T <: TaggedMessageQueue[T]](queue: T) = (None, queue.cancelMessages(tags))
  }

  implicit val messageOrdering = new Ordering[(String, Set[String], Int)] {
    def compare(x: (String, Set[String], Int), y: (String, Set[String], Int)): Int =
      -Ordering.Int.compare(x._3, y._3)
  }

  trait TaggedMessageQueue[T <: TaggedMessageQueue[T]] {
    def enqueue(msg: String, tags: Set[String], messageArrivalTime: Int): T

    def dequeueOption: Option[((Int, String), T)]

    def cancelMessages(tags: Set[String]): T
  }

  case class ActualTaggedMessageQueue(queue: MessageQueue[String, String, Int] = MessageQueue(0))
    extends TaggedMessageQueue[ActualTaggedMessageQueue] {
    def enqueue(msg: String, tags: Set[String], messageArrivalTime: Int) =
      ActualTaggedMessageQueue(queue.enqueue(msg, tags, messageArrivalTime))

    def dequeueOption = queue.dequeueOption.map {
      case (timeAndMessage, queueTail) => (timeAndMessage, ActualTaggedMessageQueue(queueTail))
    }

    def cancelMessages(tags: Set[String]) =
      ActualTaggedMessageQueue(queue.cancelMessages(tags))
  }

  case class TestTaggedMessageQueue(queue: PriorityQueue[(String, Set[String], Int)] = PriorityQueue())
    extends TaggedMessageQueue[TestTaggedMessageQueue] {
    def enqueue(message: String, tags: Set[String], messageArrivalTime: Int) =
      new TestTaggedMessageQueue(queue.enqueue((message, tags, messageArrivalTime)))

    def dequeueOption = queue.dequeueOption.map {
      case ((message, _, time), queueTail) => ((time, message), new TestTaggedMessageQueue(queueTail))
    }

    def cancelMessages(tags: Set[String]) = {
      val messages = queue.toSeq.filterNot {
        case (_, messageTags, _) => tags.exists(messageTags.contains(_))
      }
      new TestTaggedMessageQueue(PriorityQueue(messages: _*))
    }
  }

  def message(msg: String = "",
              tags: Set[String] = Set.empty,
              arrivalTime: Int = 0)
  = (msg, tags, arrivalTime)

  def messageQueue(messages: (String, Set[String], Int)*) = messages.foldLeft(MessageQueue[String, String, Int](0)) {
    case (queue, (message, tags, arrivalTime)) => queue.enqueue(message, tags, arrivalTime)
  }

}
