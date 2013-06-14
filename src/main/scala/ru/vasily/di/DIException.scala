package ru.vasily.di

class DIException(message: String, cause: Throwable = null)
  extends RuntimeException(message, cause)
