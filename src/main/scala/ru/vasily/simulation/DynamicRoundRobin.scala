package ru.vasily.simulation

import annotation.tailrec
import core._
import ru.vasily.simulation.MonitoringService.ServersLoad
import ru.vasily.simulation.MonitoringService.GetServersLoad

case class DynamicRoundRobin(serversPerformance: Seq[Double], maxWeight: Int, refreshTime: Int) extends ClusterModel {
  val numberOfServers = serversPerformance.size
  val (servers, monitoringAgents) = SimpleServer.generateAgents(serversPerformance, Some(refreshTime))
  val serverIds = servers.map(_.id)
  val defaultWeights = Vector(serversPerformance.map(_.toInt): _*)

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

    val monitoringServiceMessage = SendMessage.withoutDelay(GetServersLoad(thisAgent), MonitoringService)

    case class RoundRobinState(serversLoad: Vector[Int],
                               algorithmState: RoundRobinAlgorithmState = RoundRobinAlgorithmState())
      extends AgentState {

      def serverMessage(serverId: AgentId, task: Task) =
        SendMessage.withoutDelay(TaskMessage(thisAgent, task), serverId)

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => {
          val srvMessage = serverMessage(serverIds(algorithmState.currentServerIndex), task)
          val nextAlgState = algorithmState.nextState(serversLoad)
          val nextState = RoundRobinState(serversLoad, nextAlgState)
          newState(nextState, srvMessage, monitoringServiceMessage)
        }
        case ServersLoad(freshLoadDataMap) => {
          val freshLoadData = serverIds.map(freshLoadDataMap.get(_).getOrElse(0))
          newState(RoundRobinState(Vector(freshLoadData: _*), algorithmState))
        }
        case ServerFinishedTask(server: SimpleServer) => noChanges
      }
    }

  }

  def agents = {
    val mainServerId = MainServer()
    val zeroLoad = Vector.iterate(0, numberOfServers)(x => 0)
    val mainServerAgent = Agent(mainServerId, mainServerId.RoundRobinState(zeroLoad))

    servers ++ monitoringAgents :+ mainServerAgent :+ MonitoringService.agent
  }

  def initialMessagesReceiver = MainServer()
}
