package ru.vasily.simulation

import annotation.tailrec

case class DynamicRoundRobin(numberOfServers: Int, maxWeight: Int) extends ClusterModel {
  val serverIds = (0 until numberOfServers) map {
    SimpleServer(_)
  }
  val defaultWeights = Vector.fill(numberOfServers)(1)

  @tailrec
  private def gcd(a: Int, b: Int): Int =
    if (b > a) {
      gcd(b, a)
    } else if (b == 0) {
      a
    } else {
      gcd(a % b, b)
    }


  case class RoundRobinAlgorithmState(serverWeights: Vector[Int] = defaultWeights,
                                      currentServerIndex: Int = 0,
                                      currentWeight: Int = 0) {
    def nextState(serversLoad: Vector[Int]) = {
      val (serverWeights, currentWeight) = recalculateWeights(serversLoad)
      val (nextIndex, nextWeight) = getNextIndex(serverWeights, currentServerIndex, currentWeight)
      RoundRobinAlgorithmState(serverWeights, nextIndex, nextWeight)

    }

    def recalculateWeights(serversLoad: Vector[Int]): (Vector[Int], Int) =
      if (currentWeight == 0) {
        val weights = {
          val maxLoad = serversLoad.max
          val minLoad = serversLoad.min
          serversLoad.map(load =>
            ((maxLoad - load) * (maxWeight + 1) + 1)
              / (maxLoad - minLoad + 1)
          )
        }
        (weights, weights.max)
      } else {
        (serverWeights, currentWeight)
      }

    @tailrec private def getNextIndex(serversWeights: Vector[Int], currentServerIndex: Int, currentWeight: Int): (Int, Int) = {
      val nextIndex = (currentServerIndex + 1) % numberOfServers
      val nextWeight = if (currentServerIndex + 1 == numberOfServers) {
        currentWeight - 1
      } else {
        currentWeight
      }
      if (serversWeights(nextIndex) >= currentWeight) {
        (nextIndex, nextWeight)
      } else {
        getNextIndex(serversWeights, nextIndex, nextWeight)
      }
    }

  }


  case class MainServer() extends AgentId {

    case class RoundRobinState(serversLoad: Vector[Int],
                               algorithmState: RoundRobinAlgorithmState = RoundRobinAlgorithmState())
      extends AgentState {

      def serverMessage(serverId: AgentId, task: Task) = DelayedMessage(TaskMessage(thisAgent, task), serverId, 0)

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => {
          val nextAlgState = algorithmState.nextState(serversLoad)
          val serverIndex = nextAlgState.currentServerIndex
          val message = serverMessage(serverIds(serverIndex), task)
          val currentServerLoad = serversLoad(serverIndex)
          val nextServersLoad = serversLoad.updated(serverIndex, currentServerLoad + 1)
          val newState = RoundRobinState(nextServersLoad, nextAlgState)
          StateTransition(newState, message)
        }
        case ServerFinishedTask(SimpleServer(index)) => {
          val currentServerLoad = serversLoad(index)
          val nextServersLoad = serversLoad.updated(index, currentServerLoad - 1)
          val newState = RoundRobinState(nextServersLoad, algorithmState)
          StateTransition(newState)
        }
      }
    }

  }

  def agents = {
    val serverAgents =
      for (serverId <- serverIds)
      yield (serverId, serverId.IdleState())
    val mainServerId = MainServer()
    val zeroLoad = Vector.iterate(0, numberOfServers)(x => 0)
    val mainServerAgent = (mainServerId, mainServerId.RoundRobinState(zeroLoad))

    serverAgents :+ mainServerAgent :+ MonitoringService.agent
  }

  def initialMessagesReceiver = MainServer()
}
