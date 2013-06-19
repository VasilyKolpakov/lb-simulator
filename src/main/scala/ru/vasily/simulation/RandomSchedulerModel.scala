package ru.vasily.simulation

import core.{AgentState, Agent, AgentId}
import util.Random

class RandomSchedulerModel(seed: Int) extends SchedulerModel {

  def agent(mainServerId: AgentId, nodes: IndexedSeq[AgentId]): SchedulerAgents = {
    val id = mainServerId.child("RandomScheduler")
    val agent = Agent(id, RandomSchedulerAgentStates(mainServerId, nodes).State)
    SchedulerAgents(Seq(agent), agent.id)
  }

  case class RandomSchedulerAgentStates(mainServerId: AgentId, nodes: IndexedSeq[AgentId]) {
    val random = new Random(seed)
    val numberOfNodes = nodes.size

    object State extends AgentState {
      private def randomServerId = nodes(random.nextInt(numberOfNodes))

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case FindServerForTask(task) => newActions(
          mainServerId ! ServerFound(randomServerId, task)
        )
        case _: TaskFinished => noChanges
      }
    }

  }

}
