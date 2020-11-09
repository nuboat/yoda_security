organization := "in.norbor"
name := "yoda-security"
version := "20201109"

scalaVersion := "2.13.3"
scalacOptions := Seq("-feature", "-deprecation", "-unchecked", "-Ywarn-dead-code")
javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-encoding", "UTF-8")

updateOptions := updateOptions.value.withGigahorse(false)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.8.4" % Compile
  , "com.typesafe.play" %% "play-jdbc-api" % "2.8.4" % Compile
  , "com.typesafe.play" %% "play-cache" % "2.8.4" % Compile
  , "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2" % Compile
  , "in.norbor" %% "yoda-orm" % "20200331"
  , "net.codingwell" %% "scala-guice" % "4.2.7"
)

libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "1.4.200" % Test
  , "org.scalatest" %% "scalatest" % "3.2.0" % Test
)

publishTo := Some(
  if (isSnapshot.value) Opts.resolver.sonatypeSnapshots else Opts.resolver.sonatypeStaging
)
