import AssemblyKeys._

assemblySettings

jarName in assembly := "sim.jar"

name := "lb_simulator"

version := "1.0"

scalaVersion := "2.10.0"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.1.0"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.1.0"

libraryDependencies += "com.fasterxml.jackson.module" % "jackson-module-scala_2.10" % "2.1.3"


scalacOptions ++= Seq("-unchecked", "-deprecation")
