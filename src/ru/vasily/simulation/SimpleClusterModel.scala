package ru.vasily.simulation

import collection.immutable.Queue
import util.Random

object SimpleClusterModel {
  val numberOfServers = 2
  val maximumNetworkDelay = 10
  val maximumTaskExecutionTime = 100
  val random = new Random(0)

  case class Task(timeOfExecution: Long)

  case class TaskCompleted()

  case class MainServer() {

    case class State() extends AgentState {

      def changeState(message: DeferredMessage) = {
        val DeferredMessage(_, currentTime, contents) = message
        contents match {
          case task: Task => sendTaskToRandomServer(currentTime, task)
          case wrongMessage => {
            throw new RuntimeException("wrong message: " + wrongMessage)
          }
        }
      }

      def sendTaskToRandomServer(currentTime: Long, task: Task) = {
        val randomServerId = Server(random.nextInt(numberOfServers))
        val networkDelay = currentTime + random.nextInt(maximumNetworkDelay)
        val message: DeferredMessage = DeferredMessage(randomServerId, networkDelay, task)
        StateTransition(this, message)
      }
    }

  }

  case class Server(indexNumber: Int) {

    def completeTaskRefresher(completionTime: Long) =
      DeferredMessage(this, completionTime, TaskCompleted())

    object BusyState {
      def apply(tasks: Task*): BusyState = BusyState(Queue(tasks: _*))
    }

    case class BusyState(taskQueue: Queue[Task]) extends AgentState {

      def changeState(message: DeferredMessage) = message match {
        case DeferredMessage(_, currentTime, contents) => {
          contents match {
            case task: Task => StateTransition(BusyState(taskQueue.enqueue(task)))

            case TaskCompleted() => {
              completeCurrentTask(currentTime)
            }
          }
        }
        case wrongMessage => {
          throw new RuntimeException("wrong message: " + wrongMessage)
        }
      }


      def completeCurrentTask(currentTime: Long) = {
        val (completedTask, queueTail) = taskQueue.dequeue
        if (queueTail.isEmpty) {
          StateTransition(IdleState())
        } else {
          StateTransition(BusyState(queueTail),
            completeTaskRefresher(currentTime + queueTail.head.timeOfExecution))
        }
      }
    }

    case class IdleState() extends AgentState {
      def changeState(message: DeferredMessage) = message match {
        case DeferredMessage(_, currentTime, task: Task) => {
          val newState = BusyState(task)
          val newMessage = completeTaskRefresher(currentTime + task.timeOfExecution)
          StateTransition(newState, newMessage)
        }
        case wrongMessage => {
          throw new RuntimeException("wrong message: " + wrongMessage)
        }
      }
    }

  }

  def main(args: Array[String]) {
    val slaveServers =
      for (serverIndex <- 0 until numberOfServers;
           serverId = Server(serverIndex))
      yield (serverId, serverId.IdleState())
    val mainServerId = MainServer()
    val mainServer = (mainServerId, mainServerId.State())
    val cluster = slaveServers :+ mainServer
    val tasks = for (i <- 1 to 5) yield {
      Task(random.nextInt(maximumTaskExecutionTime))
    }
    val taskMessages = for (task <- tasks) yield {
      DeferredMessage(mainServerId, random.nextInt(maximumTaskExecutionTime / 3), task)
    }
    val initialModelState = ModelState(cluster, taskMessages)
    val modelStates = new Iterator[ModelState] {
      var nextStateOption: Option[ModelState] = Some(initialModelState)

      def hasNext = !nextStateOption.isEmpty

      def next() = {
        val currentState = nextStateOption.get
        nextStateOption = currentState.nextState()
        currentState
      }
    }
    for (modelState <- modelStates) {
      println("current time: " + modelState.currentTime)
      println("agents:")
      for (agent <- modelState.agents) {
        println(agent)
      }
      println("messages:")
      for (message <- modelState.messages) {
        println(message)
      }
      println("======================")
    }
    val tasksExecutionTimesSum: Long = tasks.map {
      case Task(time) => time
    }.sum
    println("tasks execution times sum = " + tasksExecutionTimesSum)
  }

}
