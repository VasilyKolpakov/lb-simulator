package ru.vasily.simulation

case class RoundRobinClusterModel(serversPerformance: Seq[Double]) extends ClusterModel {
  val numberOfServers = serversPerformance.size
  val serverIds = SimpleServer.generateServers(serversPerformance)

  object MainServer extends AgentId {

    case class RoundRobinState(currentServerIndex: Int = 0) extends AgentState {
      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => {
          val nextState = RoundRobinState((currentServerIndex + 1) % numberOfServers)
          val message = DelayedMessage(TaskMessage(thisAgent, task), serverIds(currentServerIndex), 0)
          StateTransition(nextState, message)
        }
        case msg: ServerFinishedTask => StateTransition(this)
      }
    }

    override def toString = "MainServer"
  }

  def agents = {
    val serverAgents =
      for (serverId <- serverIds)
      yield (serverId, serverId.IdleState())
    val mainServerId = MainServer
    val mainServerAgent = (mainServerId, mainServerId.RoundRobinState())

    serverAgents :+ mainServerAgent :+ MonitoringService.agent
  }

  def initialMessagesReceiver = MainServer
}
