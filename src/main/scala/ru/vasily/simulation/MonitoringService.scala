package ru.vasily.simulation

object MonitoringService extends AgentId {

  val agent = (MonitoringService, State())

  case class State(serversHistory: Map[AgentId, Seq[TaskRecord]] = Map.empty) extends AgentState {
    def changeState(currentTime: Long, message: AnyRef) = message match {
      case Report(serverId, taskRecord) => {
        val taskRecords = taskRecord +: serversHistory.get(serverId).getOrElse(Nil)
        val history = serversHistory.updated(serverId, taskRecords)
        StateTransition(State(history))
      }
    }
  }

  case class Report(serverId: AgentId, taskRecord: TaskRecord)

  override def toString = "MonitoringService"
}

case class TaskRecord(task: Task, completionTime: Long)

