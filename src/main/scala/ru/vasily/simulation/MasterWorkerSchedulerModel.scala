package ru.vasily.simulation

import core.{AgentState, Agent, AgentId}
import collection.immutable.Queue

object MasterWorkerSchedulerModel extends SchedulerModel {

  private case class MasterWorkerSchedulerStates(mainServerId: AgentId, nodes: IndexedSeq[AgentId]) {

    case class EmptyQueueState(freeServers: Seq[AgentId]) extends AgentState {
      def changeState(currentTime: Long, message: AnyRef) = message match {
        case FindServerForTask(task) => {
          val state = if (freeServers.tail.isEmpty) {
            AllServersAreBusyState()
          } else {
            EmptyQueueState(freeServers.tail)
          }
          newState(state, mainServerId ! ServerFound(freeServers.head, task))
        }
        case msg: TaskFinished => newState(EmptyQueueState(msg.executorId +: freeServers))
      }
    }

    case class AllServersAreBusyState(taskQueue: Queue[Task] = Queue()) extends AgentState {
      def changeState(currentTime: Long, message: AnyRef) = message match {
        case FindServerForTask(task) => newState(
          AllServersAreBusyState(taskQueue.enqueue(task))
        )
        case TaskFinished(executorId, _) =>
          if (taskQueue.isEmpty)
            newState(EmptyQueueState(Seq(executorId)))
          else
            newState(
              AllServersAreBusyState(taskQueue.tail),
              mainServerId ! ServerFound(executorId, taskQueue.head)
            )
      }
    }

  }

  def agent(mainServerId: AgentId, nodes: IndexedSeq[AgentId]): SchedulerAgents = {
    val states = MasterWorkerSchedulerStates(mainServerId, nodes)
    val schedulerId = mainServerId.child("MasterWorkerScheduler")
    val agent = Agent(schedulerId, states.EmptyQueueState(nodes))
    SchedulerAgents(Seq(agent), agent.id)
  }
}
