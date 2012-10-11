package ru.vasily.simulation

import util.Random

object RoundRobinClusterModel {
  val numberOfServers = 2
  val maximumTaskExecutionTime = 100
  val random = new Random(0)
  val serverIds = (0 until numberOfServers) map {
    SimpleServer(_)
  }

  case class MainServer() extends AgentId {

    case class RoundRobinState(currentServerIndex: Int = 0) extends AgentState {
      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => {
          val nextState = RoundRobinState((currentServerIndex + 1) % numberOfServers)
          val message = DelayedMessage(task, serverIds(currentServerIndex), 0)
          StateTransition(nextState, message)
        }
      }
    }

  }

  def main(args: Array[String]) {
    val serverAgents =
      for (serverId <- serverIds)
      yield (serverId, serverId.IdleState())
    val mainServerId = MainServer()
    val mainServerAgent = (mainServerId, mainServerId.RoundRobinState())
    val clusterAgents = serverAgents :+ mainServerAgent
    val tasks = for (i <- 1 to 10000) yield {
      Task(random.nextInt(maximumTaskExecutionTime))
    }
    val taskMessages = for (task <- tasks) yield {
      DelayedMessage(Message(task, mainServerId), random.nextInt(maximumTaskExecutionTime / 3))
    }
    val initialModelState = ModelState(clusterAgents, taskMessages)
    val modelStates = new ModelStateIterator(initialModelState)
    modelStates.next()
    val startTime = System.currentTimeMillis()
    for (modelState <- modelStates) {
    }
    println("time = " + (System.currentTimeMillis() - startTime))
    val tasksExecutionTimesSum: Long = tasks.map {
      case Task(time) => time
    }.sum
    println("tasks execution times sum = " + tasksExecutionTimesSum)
  }
}
