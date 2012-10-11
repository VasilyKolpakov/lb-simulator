package ru.vasily.simulation

import collection.immutable.Queue
import util.Random

object SimpleClusterModel {
  val numberOfServers = 2
  val maximumNetworkDelay = 10
  val maximumTaskExecutionTime = 100
  val random = new Random(0)

  case class MainServer() extends AgentId {

    case class State() extends AgentState {

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => sendTaskToRandomServer(currentTime, task)
      }

      def sendTaskToRandomServer(currentTime: Long, task: Task) = {
        val randomServerId = SimpleServer(random.nextInt(numberOfServers))
        val networkDelay = random.nextInt(maximumNetworkDelay)
        val message: DelayedMessage = DelayedMessage(task, randomServerId, networkDelay)
        StateTransition(this, message)
      }
    }

  }

  def main(args: Array[String]) {
    val slaveServers =
      for (serverIndex <- 0 until numberOfServers;
           serverId = SimpleServer(serverIndex))
      yield (serverId, serverId.IdleState())
    val mainServerId = MainServer()
    val mainServer = (mainServerId, mainServerId.State())
    val cluster = slaveServers :+ mainServer
    val tasks = for (i <- 1 to 5) yield {
      Task(random.nextInt(maximumTaskExecutionTime))
    }
    val taskMessages = for (task <- tasks) yield {
      DelayedMessage(Message(task, mainServerId), random.nextInt(maximumTaskExecutionTime / 3))
    }
    val initialModelState = ModelState(cluster, taskMessages)
    val modelStates = new ModelStateIterator(initialModelState)
    for (modelState <- modelStates) {
      println(ModelState.prettyToString(modelState))
    }
    val tasksExecutionTimesSum: Long = tasks.map {
      case Task(time) => time
    }.sum
    println("tasks execution times sum = " + tasksExecutionTimesSum)
  }

}
