package ru.vasily

trait Runner {
  def getResult: String
}

trait Result

case class FileContents(extension:String)
