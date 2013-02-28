package ru.vasily.simulation

import core._
// TODO: remove "non-models" arguments
class SimpleClusterModel(serversPerformance: Seq[Double],
                         schedulerModel: SchedulerModel,
                         monitoringServiceModel: MonitoringServiceModel,
                         masterAgentId: AgentId) extends ClusterModel {
  val numberOfServers = serversPerformance.size
  val servers = SimpleServer.generateServers(serversPerformance)
  val serverIds = servers.map(_.id)
  val monitoringAgents = monitoringServiceModel.agents(serverIds)
  val mainServerId = MainServer()
  val schedulerAgent = schedulerModel.agent(mainServerId, serverIds.toIndexedSeq, MonitoringService)
  val schedulerId = schedulerAgent.id

  case class MainServer() extends AgentId

  class State(schedulerId: AgentId) extends AgentState {
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

  def agents = {
    val mainServerAgent = Agent(mainServerId, new State(schedulerId))

    monitoringAgents ++ servers :+ mainServerAgent :+ schedulerAgent :+ MonitoringService.agent
  }

  def initialMessagesReceiver: AgentId = mainServerId
}
