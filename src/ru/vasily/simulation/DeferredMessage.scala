package ru.vasily.simulation

//todo: sender id?
case class DeferredMessage(receiverId: AnyRef, timestamp: Long, contents: AnyRef) extends Ordered[DeferredMessage] {

  /*
 * A DeferredMessage with an earlier timestamp is "greater" a message with a later one.
 * It is useful when messages are stored in priority queue.
 * */
  def compare(that: DeferredMessage) = {
    val timeCompare = -this.timestamp.compare(that.timestamp)
    if (timeCompare == 0) {
      val recieverCompare = identityCompare(this.receiverId, that.receiverId)
      if (recieverCompare == 0) {
        identityCompare(this.contents, that.contents)
      } else {
        recieverCompare
      }
    } else {
      timeCompare
    }
  }

  private def identityCompare(a: AnyRef, b: AnyRef) = System.identityHashCode(a) - System.identityHashCode(b)

}
