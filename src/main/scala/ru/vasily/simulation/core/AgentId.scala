package ru.vasily.simulation.core

/**
 * marker trait for agent ids
 */

case class AgentId(name: String, parent: Option[AgentId] = None) {

  def child(childName: String) = AgentId(childName, Some(this))

  def fullName: String = parent.map(_.fullName).getOrElse("") + "/" + name

  def !(message: AnyRef) = SendMessage.withoutDelay(message, this)

  override def toString = fullName
}

