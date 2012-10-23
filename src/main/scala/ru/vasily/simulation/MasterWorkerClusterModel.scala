package ru.vasily.simulation

import collection.immutable.Queue

case class MasterWorkerClusterModel(serversPerformance: Seq[Double]) extends ClusterModel {
  val numberOfServers = serversPerformance.size
  val (servers, monitoringAgents) = SimpleServer.generateAgents(serversPerformance)
  val serverIds = servers.map(_.id)

  object MainServer extends AgentId {
    override def toString = "MainServer"

    def serverMessage(serverId: AgentId, task: Task) = DelayedMessage(TaskMessage(this, task), serverId, 0)

    case class EmptyQueueState(freeServers: Seq[AgentId] = serverIds) extends AgentState {

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => processTask(task)
        case ServerFinishedTask(serverId) => StateTransition(EmptyQueueState(serverId +: freeServers))
      }

      def processTask(task: Task) = {
        val message: DelayedMessage = serverMessage(freeServers.head, task)
        if (freeServers.tail.isEmpty) {
          StateTransition(AllServersAreBusyState(), message)
        } else {
          StateTransition(EmptyQueueState(freeServers.tail), message)
        }
      }
    }

    case class AllServersAreBusyState(taskQueue: Queue[Task] = Queue()) extends AgentState {

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => {
          StateTransition(AllServersAreBusyState(taskQueue.enqueue(task)))
        }
        case ServerFinishedTask(serverId) => processIdleServer(serverId)
      }

      def processIdleServer(serverId: AgentId): StateTransition[AgentState with Product] = {
        if (taskQueue.isEmpty) {
          StateTransition(EmptyQueueState(Seq(serverId)))
        } else {
          val message = serverMessage(serverId, taskQueue.head)
          StateTransition(AllServersAreBusyState(taskQueue.tail), message)
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
