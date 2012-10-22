package ru.vasily.simulation

import util.Random

case class RandomClusterModel(serversPerformance: Seq[Double]) extends ClusterModel {
  val numberOfServers = serversPerformance.size
  val serverIds = SimpleServer.generateServers(serversPerformance)

  object MainServer extends AgentId {
    val random = new Random(0)

    case class State() extends AgentState {

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => sendTaskToRandomServer(currentTime, task)
        case msg: ServerFinishedTask => StateTransition(this)
      }

      def sendTaskToRandomServer(currentTime: Long, task: Task) = {
        val randomServerId = serverIds(random.nextInt(numberOfServers))
        val message = DelayedMessage(TaskMessage(thisAgent, task), randomServerId, 0)
        StateTransition(this, message)
      }
    }

    override def toString = "MainServer"
  }

  def initialMessagesReceiver = MainServer

  def agents = {
    val slaveServers =
      for (serverId <- serverIds)
      yield (serverId, serverId.IdleState())
    val mainServerId = MainServer
    val mainServer = (mainServerId, mainServerId.State())
    slaveServers :+ mainServer :+ MonitoringService.agent
  }

}
