package ru.vasily.simulation

case class RoundRobinClusterModel(serversPerformance: Seq[Double]) extends ClusterModel {
  val numberOfServers = serversPerformance.size
  val (servers, monitoringAgents) = SimpleServer.generateAgents(serversPerformance)
  val serverIds = servers.map(_.id)

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
    val mainServerAgent = Agent(MainServer, MainServer.RoundRobinState())

    monitoringAgents ++ servers :+ mainServerAgent :+ MonitoringService.agent
  }

  def initialMessagesReceiver = MainServer
}
