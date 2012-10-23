package ru.vasily.simulation

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ModelStateTest extends FlatSpec with ShouldMatchers {
  "A ModelState" should "hold current time" in {
    case class Tic()

    case class Clock() extends AgentId

    case class ClockState(currentClockTime: Long) extends AgentState {
      val ticTime = 1

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case Tic() => {
          val ticMessage = DelayedMessage(Tic(), Clock(), ticTime)
          currentTime should equal(currentClockTime)
          StateTransition(ClockState(currentClockTime + ticTime), ticMessage)
        }
      }
    }
    val initialTic: DelayedMessage = DelayedMessage(Tic(), Clock(), 0)
    val clockAgent = Agent(Clock(), ClockState(0), initialTic)
    val initialState = ModelState(Seq(clockAgent))
    initialState.nextStates.take(10).last
  }


}
