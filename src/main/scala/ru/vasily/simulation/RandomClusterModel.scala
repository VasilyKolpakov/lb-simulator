package ru.vasily.simulation

import core._
import util.Random

case class RandomClusterModel(serversPerformance: Seq[Double], seed: Int) extends ClusterModel {
  val numberOfServers = serversPerformance.size
  val (servers, monitoringAgents) = SimpleServer.generateAgents(serversPerformance)
  val serverIds = servers.map(_.id)

  object MainServer extends AgentId {
    val random = new Random(seed)

    case class State() extends AgentState {

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => sendTaskToRandomServer(currentTime, task)
        case msg: ServerFinishedTask => noChanges
      }

      def sendTaskToRandomServer(currentTime: Long, task: Task) = {
        val randomServerId = serverIds(random.nextInt(numberOfServers))
        val message = SendMessage.withoutDelay(TaskMessage(thisAgent, task), randomServerId)
        newActions(message)
      }
    }

    override def toString = "MainServer"
  }

  def initialMessagesReceiver = MainServer

  def agents = {
    val mainServer = Agent(MainServer, MainServer.State())
    servers ++ monitoringAgents :+ mainServer :+ MonitoringService.agent
  }

}
