package ru.vasily.simulation

import core.{Agent, AgentState, AgentId}
import annotation.tailrec
import ru.vasily.simulation.MonitoringService.{GetServersLoad, ServersLoad}

case class DynamicRoundRobinSchedulerModel(maxWeight: Int, monitoringModel: MonitoringServiceModel) extends SchedulerModel {
  def agent(mainServerId: AgentId, nodes: IndexedSeq[AgentId]): SchedulerAgents = {
    val MonitoringAgents(monitoringServiceAgents, monitoringServiceId) = monitoringModel.agents(nodes)
    val schedulerAgentId = DynamicRoundRobinScheduler(mainServerId, nodes, monitoringServiceId)
    val defaultWeights = Vector(nodes.collect {
      case s: SimpleServer => s.serverPerformance.toInt
    }: _*)
    val schedulerAgent =
      Agent(schedulerAgentId, schedulerAgentId.RoundRobinState(nodes.map(_ => 0).toVector, RoundRobinAlgorithmState(defaultWeights)))
    SchedulerAgents(schedulerAgent +: monitoringServiceAgents, schedulerAgentId)
  }

  private case class DynamicRoundRobinScheduler(mainServerId: AgentId,
                                                nodes: IndexedSeq[AgentId],
                                                monitoringService: AgentId) extends AgentId {

    case class RoundRobinState(serversLoad: Vector[Int],
                               algorithmState: RoundRobinAlgorithmState)
      extends AgentState {
      val monitoringServiceMessage = monitoringService ! GetServersLoad(thisAgent)

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case FindServerForTask(task) => {
          val serverId = nodes(algorithmState.currentServerIndex)
          val serverFoundAction = mainServerId ! ServerFound(serverId, task)
          val nextAlgState = algorithmState.nextState(serversLoad)
          val nextState = RoundRobinState(serversLoad, nextAlgState)
          newState(nextState, serverFoundAction, monitoringServiceMessage)
        }
        case ServersLoad(freshLoadDataMap) => {
          val freshLoadData = nodes.map(freshLoadDataMap.get(_).getOrElse(0))
          newState(RoundRobinState(Vector(freshLoadData: _*), algorithmState))
        }
        case msg: TaskFinished => noChanges
      }
    }

  }


  private case class RoundRobinAlgorithmState(serverWeights: Vector[Int],
                                              currentServerIndex: Int = 0,
                                              currentWeight: Int = 0) {
    val numberOfNodes = serverWeights.size

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
      val nextIndex = (currentServerIndex + 1) % numberOfNodes
      val nextWeight = if (currentServerIndex + 1 == numberOfNodes) {
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

    @tailrec
    private def gcd(a: Int, b: Int): Int =
      if (b > a) {
        gcd(b, a)
      } else if (b == 0) {
        a
      } else {
        gcd(a % b, b)
      }

  }

}