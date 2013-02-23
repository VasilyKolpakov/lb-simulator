package ru.vasily.core

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite
import collection.mutable.{PriorityQueue => ScalaPriorityQueue, ArrayBuffer}
import collection.immutable.IndexedSeq
import collection.mutable

class PriorityQueueTest extends FunSuite with ShouldMatchers {
  test("isEmpty returns false on non-empty queue") {
    PriorityQueue(1, 2).isEmpty should equal(false)
  }
  test("isEmpty returns true on empty queue") {
    PriorityQueue[Int]().isEmpty should equal(true)
  }
  test("the queue works as scala standart PriorityQueue") {
    val randomNumbers = Seq.fill(10000)(math.random)
    val scalaQueue = ScalaPriorityQueue(randomNumbers: _*)
    val buffer = ArrayBuffer[Double]()
    while (!scalaQueue.isEmpty) {
      buffer += scalaQueue.dequeue()
    }
    val actual = PriorityQueue(randomNumbers: _*).toSeq.toList
    actual should equal(buffer.toList)
  }
  test("queue is stable") {
    case class TestElem(i: Int, order: Int = 0)
    val testElems: IndexedSeq[TestElem] = (1 to 4).map(TestElem(_))
    val _queue = PriorityQueue(testElems: _*)(new Ordering[TestElem] {
      def compare(x: TestElem, y: TestElem) = x.order - y.order
    })

    val queue = _queue.enqueue(TestElem(100, 100)).enqueue(TestElem(100, 1000)).dequeueOption.get._2.dequeueOption.get._2

    queue.toSeq.toList should equal(testElems.toList)
  }
  test("head method works") {
    PriorityQueue(1, 2, 3, 4).head should equal(4)
  }
  test("toSeq method is synced with dequeue") {
    val randomNumbers = Seq.fill(10000)(math.random)
    val pQueue = PriorityQueue(randomNumbers: _*)
    toStreamViaDequeue(pQueue).toSeq should equal(pQueue.toSeq)
  }

  test("toSeq method is \"stable\"") {
    val randomNumbers = Seq.fill(10)(math.random)
    val queue = PriorityQueue(randomNumbers: _*)
    PriorityQueue(queue.toSeq: _*) should equal(queue)
  }

  def toStreamViaDequeue[A](queue: PriorityQueue[A]): Stream[A] = queue.dequeueOption.map {
    pair => {
      val (number: A, queueTail: PriorityQueue[A]) = pair
      Stream.cons(number, toStreamViaDequeue(queueTail))
    }
  }.getOrElse(Stream.empty)

}
