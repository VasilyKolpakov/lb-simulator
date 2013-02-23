package ru.vasily.simulation

import collection.immutable.Queue
import core._

case class MasterWorkerClusterModel(serversPerformance: Seq[Double]) extends ClusterModel {
  val numberOfServers = serversPerformance.size
  val (servers, monitoringAgents) = SimpleServer.generateAgents(serversPerformance)
  val serverIds = servers.map(_.id)

  object MainServer extends AgentId {
    override def toString = "MainServer"

    def serverMessage(serverId: AgentId, task: Task) =
      SendMessage.withoutDelay(TaskMessage(this, task), serverId)

    case class EmptyQueueState(freeServers: Seq[AgentId] = serverIds) extends AgentState {

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => processTask(task)
        case ServerFinishedTask(serverId) => newState(EmptyQueueState(serverId +: freeServers))
      }

      def processTask(task: Task) = {
        val message = serverMessage(freeServers.head, task)
        if (freeServers.tail.isEmpty) {
          newState(AllServersAreBusyState(), message)
        } else {
          newState(EmptyQueueState(freeServers.tail), message)
        }
      }
    }

    case class AllServersAreBusyState(taskQueue: Queue[Task] = Queue()) extends AgentState {

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => {
          newState(AllServersAreBusyState(taskQueue.enqueue(task)))
        }
        case ServerFinishedTask(serverId) => processIdleServer(serverId)
      }

      def processIdleServer(serverId: AgentId) = {
        if (taskQueue.isEmpty) {
          newState(EmptyQueueState(Seq(serverId)))
        } else {
          val message = serverMessage(serverId, taskQueue.head)
          newState(AllServersAreBusyState(taskQueue.tail), message)
        }
      }
    }

  }

  def agents = {
    val mainServerAgent = Agent(MainServer, MainServer.EmptyQueueState())
    servers ++ monitoringAgents :+ mainServerAgent :+ MonitoringService.agent
  }

  def initialMessagesReceiver = MainServer
}
