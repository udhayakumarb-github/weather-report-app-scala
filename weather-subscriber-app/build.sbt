ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
    name := "weather-report-subscriber-app"
  )

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.8.8",
  "com.typesafe.akka" %% "akka-http" % "10.5.3",
  "com.typesafe.akka" %% "akka-stream" % "2.8.8",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.5.3",
  "com.typesafe.akka" %% "akka-actor-typed" % "2.8.8",
  "com.typesafe.akka" %% "akka-stream-typed" % "2.8.8",
  "org.mongodb.scala" % "mongo-scala-driver_2.13" % "5.3.0",
  "com.typesafe.play" %% "play-json" % "2.10.6",
  "io.spray" %% "spray-json" % "1.3.6",
  "org.jsoup" % "jsoup" % "1.18.3",
  "ch.qos.logback" % "logback-classic" % "1.5.16",
  "org.apache.commons" % "commons-email" % "1.6.0"
)