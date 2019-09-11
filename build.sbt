name := "KafkaExample"

version := "0.1"

scalaVersion := "2.12.8"

lazy val root = (project in file("."))
  .settings(
    name := "root",
    libraryDependencies ++= Seq(
      "org.apache.kafka" %% "kafka" % "2.0.0",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.8"
    )
  )