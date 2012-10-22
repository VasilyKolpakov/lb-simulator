package ru.vasily

trait Runner {
  def getResult: AnyRef

  def getResultString: String = Serializer.marshal(getResult)
}
