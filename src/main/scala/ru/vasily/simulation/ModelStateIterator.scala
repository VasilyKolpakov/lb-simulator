package ru.vasily.simulation

class ModelStateIterator(initialModelState: ModelState) extends Iterator[ModelState] {
  var nextStateOption: Option[ModelState] = Some(initialModelState)

  def hasNext = !nextStateOption.isEmpty

  def next() = {
    val currentState = nextStateOption.get
    nextStateOption = currentState.nextState()
    currentState
  }
}
