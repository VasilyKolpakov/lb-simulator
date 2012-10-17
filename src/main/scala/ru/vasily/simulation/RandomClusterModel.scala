package ru.vasily.simulation

import util.Random

class RandomClusterModel(numberOfServers: Int) extends ClusterModel {

  object MainServer extends AgentId {
    val random = new Random(0)

    case class State() extends AgentState {

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => sendTaskToRandomServer(currentTime, task)
        case msg: ServerFinishedTask => StateTransition(this)
      }

      def sendTaskToRandomServer(currentTime: Long, task: Task) = {
        val randomServerId = SimpleServer(random.nextInt(numberOfServers))
        val message = DelayedMessage(TaskMessage(thisAgent, task), randomServerId, 0)
        StateTransition(this, message)
      }
    }

    override def toString = "MainServer"
  }

  def initialMessagesReceiver = MainServer

  def agents = {
    val slaveServers =
      for (serverIndex <- 0 until numberOfServers;
           serverId = SimpleServer(serverIndex))
      yield (serverId, serverId.IdleState())
    val mainServerId = MainServer
    val mainServer = (mainServerId, mainServerId.State())
    slaveServers :+ mainServer :+ MonitoringService.agent
  }

}
