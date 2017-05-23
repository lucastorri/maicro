
lazy val root = (project in file(".")).settings(
  name := "maicro",
  organization := "com.unstablebuild",
  scalaVersion := "2.12.2",
  version      := "0.1.0",
  libraryDependencies ++= Seq(
    "com.typesafe.play" % "play-json_2.12" % "2.6.0-RC1",
    "org.scalatest" %% "scalatest" % "3.0.1" % Test
  )
)
