organization := "in.norbor"
name := "yoda-security"
version := "20200330"

scalaVersion := "2.13.1"
scalacOptions := Seq("-feature", "-deprecation", "-unchecked", "-Ywarn-dead-code")

updateOptions := updateOptions.value.withGigahorse(false)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.8.1" % Compile
  , "com.typesafe.play" %% "play-jdbc-api" % "2.8.1" % Compile
  , "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2" % Compile
  , "in.norbor" %% "yoda-orm" % "4.0.9"
)

libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "1.4.194" % Test
  , "org.scalatest" %% "scalatest" % "3.1.1" % Test
)

publishTo := Some(
  if (isSnapshot.value) Opts.resolver.sonatypeSnapshots else Opts.resolver.sonatypeStaging
)
