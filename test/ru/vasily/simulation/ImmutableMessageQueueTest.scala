package ru.vasily.simulation

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ImmutableMessageQueueTest extends FlatSpec with ShouldMatchers {
  "A ImmutableMessageQueue" should "correctly display simple-case emptyness" in {
    ImmutableMessageQueue[AnyRef]().isEmpty should be(true)
  }

  it should "correctly display complex-case emptyness" in {
    val ((_, _), emptyQueue) = ImmutableMessageQueue[Int]((1, 4)).dequeue
    emptyQueue.isEmpty should be(true)
  }

  it should "correctly display non-emptyness" in {
    ImmutableMessageQueue((1, 2)).isEmpty should be(false)
  }

  it should "dequeue messages having equal timestamps in FIFO order" in {
    val queue = ImmutableMessageQueue((0, "1"), (0, "2"))
    val ((_, firstElement), tail) = queue.dequeue
    val ((_, secondElement), _) = tail.dequeue
    firstElement should equal("1")
    secondElement should equal("2")
  }

  it should "dequeue message having the earliest timestamp" in {
    val queue = ImmutableMessageQueue((1, "1"), (0, "2"))
    val ((_, firstElement), tail) = queue.dequeue
    firstElement should equal("2")
  }

  it should "convert to sequence correctly" in {
    val seq: Seq[(Long, String)] = Seq((0, "1"), (1, "2"))
    val queue = ImmutableMessageQueue(seq: _*)
    queue.toSeq should equal(seq)
  }

  it should "return head" in {
    val queue = ImmutableMessageQueue((0, "1"), (1, "2"))
    queue.head should equal(0, "1")
  }
}
