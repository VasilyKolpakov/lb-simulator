package ru.vasily.simulation

import core._

// TODO: remove "non-models" arguments
class SimpleClusterModel(serversPerformance: Seq[Double],
                         schedulerModel: SchedulerModel) extends ClusterModel {
  val numberOfServers = serversPerformance.size

  case class SimpleClusterStates(mainServerId: AgentId) {

    case class State(schedulerId: AgentId, masterAgentId: AgentId) extends AgentState {
      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => newActions(
          schedulerId ! FindServerForTask(task)
        )
        case ServerFound(serverId, task) => newActions(
          serverId ! TaskMessage(mainServerId, task)
        )
        case msg: TaskFinished => newActions(
          msg.forward(masterAgentId),
          schedulerId ! msg
        )
      }
    }

  }

  def agents(masterId: AgentId) = {
    val mainServerId = masterId.child("SimpleCluster")
    val servers = SimpleServer.generateServers(serversPerformance, mainServerId)
    val serverIds = servers.map(_.id)
    val SchedulerAgents(schedulerAgents, schedulerId) = schedulerModel.agent(mainServerId, serverIds.toIndexedSeq)
    val clusterStates = SimpleClusterStates(mainServerId)
    val mainServerAgent = Agent(mainServerId, clusterStates.State(schedulerId, masterId))

    ClusterAgents(schedulerAgents ++ servers :+ mainServerAgent, mainServerId)
  }
}
