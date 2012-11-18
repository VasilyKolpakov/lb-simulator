package ru.vasily

trait Runner {
  def getResult: Result
}

trait Result

case class FileContents(contents: String, extension: String) extends Result

case class ExecScript(outputFileNamePrefixToScript: String => String,
                      scriptExtension: String,
                      scriptFileNameToCommand: String => String) extends Result
