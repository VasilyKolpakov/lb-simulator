package ru.vasily.simulation

import core._
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite
import org.scalatest.OptionValues._
import ru.vasily.simulation.MonitoringService.{ServersLoad, GetServersLoad}


class SimpleServerTest extends FunSuite with ShouldMatchers {
  test("monitoring is working") {

    case object CheckMessage

    case class TestDummyState(serverLoad: Option[Int], monitoringServiceId: AgentId) extends AgentState {
      def changeState(currentTime: Long, message: AnyRef) = message match {
        case CheckMessage => newActions(SendMessage.withoutDelay(
          contents = GetServersLoad(DummyAgent),
          receiverId = monitoringServiceId)
        )
        case ServersLoad(serversLoad) => newState(copy(serverLoad = Some(serversLoad.values.head)))
      }
    }

    def readServerLoad(state: ModelState): Option[Int] = state.agents(DummyAgent).asInstanceOf[TestDummyState].serverLoad
    def isFinalState(state: ModelState) = readServerLoad(state).isDefined

    val refreshTime: Int = 5
    val numberOfTasks: Int = 10
    val serverSeq = SimpleServer.generateServers(serversPerformance = Seq(1.0))
    val MonitoringAgents(monitoringAgentsSeq, monitoringAgentsId) = MonitoringServiceModel(refreshTime).agents(serverSeq.map(_.id))
    def taskMessage(messageIndex: Int) = TaskMessage(DummyAgent, Task(messageIndex, 10, 0))
    val taskMessages = (1 to numberOfTasks).map(i => SendMessage.withoutDelay(taskMessage(i), serverSeq.head.id)).toList
    val checkMessage = SendMessage(Message(CheckMessage, DummyAgent), 5)

    val dummyAgent = Agent(DummyAgent, TestDummyState(None, monitoringAgentsId), checkMessage :: taskMessages)

    val allTasksAreSubmittedState = ModelState(serverSeq ++ monitoringAgentsSeq :+ dummyAgent)
      .nextStates.find {
      case (state, logs) => isFinalState(state)
    }.map(_._1)

    readServerLoad(allTasksAreSubmittedState.value).value should equal(numberOfTasks)
  }
}
