package ru.vasily.simulation.core

/**
 * marker trait for agent ids
 */
trait AgentId {
  def thisAgent: this.type = this
}
