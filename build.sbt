import AssemblyKeys._

assemblySettings

jarName in assembly := "sim.jar"

name := "lb_simulator"

version := "1.0"

scalaVersion := "2.9.1"

libraryDependencies += "org.scalatest" % "scalatest_2.9.0" % "1.8" % "test"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.1.0"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.1.0"

libraryDependencies += "com.fasterxml.jackson.module" % "jackson-module-scala" % "2.1.0"


scalacOptions ++= Seq("-unchecked", "-deprecation")
