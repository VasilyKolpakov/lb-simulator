package ru.vasily.simulation.core

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class ModelStateTest extends FunSuite with ShouldMatchers {
  test("clock case") {
    case object Tic

    val clockId = AgentId("Clock")

    case class ClockState(currentClockTime: Long) extends AgentState {
      val ticTime = 1

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case Tic => {
          val ticMessage = SendMessage(
            message = Message(Tic, clockId),
            delay = ticTime
          )
          newState(ClockState(currentClockTime + ticTime), ticMessage)
        }
      }
    }
    val initialTic = SendMessage.withoutDelay(Tic, clockId)
    val clockAgent = Agent(clockId, ClockState(0)).withInitialActions(initialTic)
    val initialState = ModelState(Seq(clockAgent))
    val clockState = initialState.nextStates(9).agents(clockId)
    clockState.asInstanceOf[ClockState].currentClockTime should equal(10)
  }

  test("messages can be cancelled") {
    case class Tic()

    val clockId = AgentId("Clock")
    case object TicTag extends MessageTag

    val sendTicMessageAction = SendMessage(
      message = Message(Tic(), clockId),
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
    val clockAgent = Agent(clockId, ClockState(0), List.fill(10)(sendTicMessageAction))
    val initialState = ModelState(Seq(clockAgent))
    val clockState = initialState.nextStates.last.agents(clockId)
    clockState.asInstanceOf[ClockState].currentClockTime should equal(5)
  }

  test("logging works") {
    case class Tic()

    val clockId = AgentId("Clock")

    case object TicTag extends MessageTag

    val sendTicMessageAction = SendMessage(
      message = Message(Tic(), clockId),
      delay = 1,
      tags = Set(TicTag)
    )

    case class ClockState(currentClockTime: Long) extends AgentState {
      def changeState(currentTime: Long, message: AnyRef) = message match {
        case Tic() => {
          val actions = Seq(sendTicMessageAction, LogAction(currentClockTime))
          StateTransition(ClockState(currentClockTime + 1), actions)
        }
      }
    }
    val clockAgent = Agent(clockId, ClockState(0), List.fill(10)(sendTicMessageAction))
    val initialState = ModelState(Seq(clockAgent))
    val logs = initialState.logsUntil(_.agents(clockId).asInstanceOf[ClockState].currentClockTime == 10)
    logs.map(log => log.record) should equal((0 until 9).toStream)
  }

}
