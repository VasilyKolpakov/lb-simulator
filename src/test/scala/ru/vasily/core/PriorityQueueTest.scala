package ru.vasily.core

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite
import collection.mutable.{PriorityQueue => ScalaPriorityQueue, ArrayBuffer}
import collection.immutable.IndexedSeq

class PriorityQueueTest extends FunSuite with ShouldMatchers {
  test("isEmpty returns false on non-empty queue") {
    PriorityQueue(1, 2).isEmpty should equal(false)
  }
  test("isEmpty returns true on empty queue") {
    PriorityQueue[Int]().isEmpty should equal(true)
  }
  test("the queue works as scala standart PriorityQueue") {
    val randomNumbers = Seq.fill(1000)(math.random)
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

    val queue = _queue.enqueue(TestElem(100, 100)).enqueue(TestElem(100, 1000)).dequeue._2.dequeue._2

    queue.toSeq.toList should equal(testElems.toList)
  }

}
