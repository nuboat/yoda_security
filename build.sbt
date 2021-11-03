organization := "in.norbor"
name := "yoda-security"
version := "1.3.2"

scalaVersion := "2.13.6"
scalacOptions := Seq("-feature", "-deprecation", "-unchecked", "-Ywarn-dead-code")
javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-encoding", "UTF-8")

updateOptions := updateOptions.value.withGigahorse(false)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.8.8" % Compile
  , "com.typesafe.play" %% "play-jdbc-api" % "2.8.8" % Compile
  , "com.typesafe.play" %% "play-cache" % "2.8.8" % Compile
  , "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
  , "in.norbor" %% "yoda-orm" % "5.1.0"
  , "net.codingwell" %% "scala-guice" % "4.2.11"
)

libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "1.4.200" % Test
  , "org.scalatest" %% "scalatest" % "3.2.6" % Test
)

publishTo := Some(
  if (isSnapshot.value) Opts.resolver.sonatypeSnapshots else Opts.resolver.sonatypeStaging
)
