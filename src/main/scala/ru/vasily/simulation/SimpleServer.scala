package ru.vasily.simulation

import collection.immutable.Queue

case class SimpleServer(indexNumber: Int) extends AgentId {
  def taskCompletedMessage(task: Task) =
    DelayedMessage(TaskCompleted(), this, task.timeOfExecution)

  case class TaskCompleted()

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