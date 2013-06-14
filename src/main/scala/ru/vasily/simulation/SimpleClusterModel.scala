package ru.vasily.simulation

import core._

// TODO: remove "non-models" arguments
class SimpleClusterModel(serversPerformance: Seq[Double],
                         schedulerModel: SchedulerModel) extends ClusterModel {
  val numberOfServers = serversPerformance.size
  val servers = SimpleServer.generateServers(serversPerformance)
  val serverIds = servers.map(_.id)
  val mainServerId = MainServer()
  val SchedulerAgents(schedulerAgents, schedulerId) = schedulerModel.agent(mainServerId, serverIds.toIndexedSeq)

  case class MainServer() extends AgentId

  class State(schedulerId: AgentId, masterAgentId: AgentId) extends AgentState {
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


  def agents(masterId: AgentId) = {
    val mainServerAgent = Agent(mainServerId, new State(schedulerId, masterId))

    ClusterAgents(schedulerAgents ++ servers :+ mainServerAgent, mainServerId)
  }
}
