package ru.vasily.simulation.core

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class ModelStateTest extends FunSuite with ShouldMatchers {
  test("clock case") {
    case object Tic

    case object Clock extends AgentId

    case class ClockState(currentClockTime: Long) extends AgentState {
      val ticTime = 1

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case Tic => {
          val ticMessage = SendMessage(
            message = Message(Tic, Clock),
            delay = ticTime
          )
          newState(ClockState(currentClockTime + ticTime), ticMessage)
        }
      }
    }
    val initialTic = SendMessage.withoutDelay(Tic, Clock)
    val clockAgent = Agent(Clock, ClockState(0), initialTic)
    val initialState = ModelState(Seq(clockAgent))
    val clockState = initialState.nextStates(9).agents(Clock)
    clockState.asInstanceOf[ClockState].currentClockTime should equal(10)
  }

  test("messages can be cancelled") {
    case class Tic()

    case object Clock extends AgentId
    case object TicTag extends MessageTag

    val sendTicMessageAction = SendMessage(
      message = Message(Tic(), Clock),
      delay = 1,
      tags = Set(TicTag)
    )

    case class ClockState(currentClockTime: Long) extends AgentState {
      def changeState(currentTime: Long, message: AnyRef) = message match {
        case Tic() => {
          val actions = Seq(CancelMessages(TicTag))
            .filter(_ => currentClockTime == 4)
          StateTransition(ClockState(currentClockTime + 1), actions)
        }
      }
    }
    val clockAgent = Agent(Clock, ClockState(0), List.fill(10)(sendTicMessageAction))
    val initialState = ModelState(Seq(clockAgent))
    val clockState = initialState.nextStates.last.agents(Clock)
    clockState.asInstanceOf[ClockState].currentClockTime should equal(5)
  }
}
