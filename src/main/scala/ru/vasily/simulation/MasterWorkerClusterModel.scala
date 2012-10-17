package ru.vasily.simulation

import collection.immutable.Queue

class MasterWorkerClusterModel(numberOfServers: Int) extends ClusterModel {
  val serverIds = (0 until numberOfServers) map {
    SimpleServer(_)
  }


  def agents = {
    val serverAgents =
      for (serverId <- serverIds)
      yield (serverId, serverId.IdleState())
    val mainServerId = MainServer
    val mainServerAgent = (mainServerId, mainServerId.EmptyQueueState())

    serverAgents :+ mainServerAgent :+ MonitoringService.agent
  }

  def initialMessagesReceiver = MainServer

  object MainServer extends AgentId {
    override def toString = "MainServer"

    def serverMessage(serverId: AgentId, task: Task) = DelayedMessage(TaskMessage(this, task), serverId, 0)

    case class EmptyQueueState(freeServers: Seq[AgentId] = serverIds) extends AgentState {

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => {
          val message: DelayedMessage = serverMessage(freeServers.head, task)
          if (freeServers.tail.isEmpty) {
            StateTransition(AllServersAreBusyState(), message)
          } else {
            StateTransition(EmptyQueueState(freeServers.tail), message)
          }
        }
        case ServerFinishedTask(serverId) => StateTransition(EmptyQueueState(serverId +: freeServers))
      }
    }

    case class AllServersAreBusyState(taskQueue: Queue[Task] = Queue()) extends AgentState {

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => {
          StateTransition(AllServersAreBusyState(taskQueue.enqueue(task)))
        }
        case ServerFinishedTask(serverId) => {
          if (taskQueue.isEmpty) {
            StateTransition(EmptyQueueState(Seq(serverId)))
          } else {
            val message = serverMessage(serverId, taskQueue.head)
            StateTransition(AllServersAreBusyState(taskQueue.tail), message)
          }
        }
      }
    }

  }

}
