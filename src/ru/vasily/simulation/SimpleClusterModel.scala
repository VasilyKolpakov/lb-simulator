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

  case class MainServer() extends AgentId {

    case class State() extends AgentState {

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => sendTaskToRandomServer(currentTime, task)
      }

      def sendTaskToRandomServer(currentTime: Long, task: Task) = {
        val randomServerId = Server(random.nextInt(numberOfServers))
        val networkDelay = random.nextInt(maximumNetworkDelay)
        val message: DelayedMessage = DelayedMessage(task, randomServerId, networkDelay)
        StateTransition(this, message)
      }
    }

  }

  case class Server(indexNumber: Int) extends AgentId {

    def taskCompletedMessage(task: Task) =
      DelayedMessage(TaskCompleted(), this, task.timeOfExecution)

    object BusyState {
      def apply(tasks: Task*): BusyState = BusyState(Queue(tasks: _*))
    }

    case class BusyState(taskQueue: Queue[Task]) extends AgentState {

      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => StateTransition(BusyState(taskQueue.enqueue(task)))

        case TaskCompleted() => completeCurrentTask()
      }


      def completeCurrentTask() = {
        val (completedTask, queueTail) = taskQueue.dequeue
        if (queueTail.isEmpty) {
          StateTransition(IdleState())
        } else {
          StateTransition(BusyState(queueTail),
            taskCompletedMessage(queueTail.head))
        }
      }
    }

    case class IdleState() extends AgentState {
      def changeState(currentTime: Long, message: AnyRef) = message match {
        case task: Task => {
          val newState = BusyState(task)
          val newMessage = taskCompletedMessage(task)
          StateTransition(newState, newMessage)
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
      DelayedMessage(Message(task, mainServerId), random.nextInt(maximumTaskExecutionTime / 3))
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
      println("time of last event: " + modelState.timeOfLastEvent)
      println("time of next event: " + modelState.timeOfNextEvent.getOrElse("-"))
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
