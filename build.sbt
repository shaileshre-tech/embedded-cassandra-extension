import DatabaseMigrations._
import Dependencies._

resolvers += Resolver.sonatypeRepo("public")

lazy val root = (project in file("."))
  .dependsOn(`embedded-cassandra-extension-core`, `embedded-cassandra-extension-example`)
  .aggregate(`embedded-cassandra-extension-core`, `embedded-cassandra-extension-example`)
  .settings(
    inThisBuild(List(
      organization := "com.myorg",
      scalaVersion := "2.11.8",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "embedded-cassandra-extension"
  ).enablePlugins(Plugin)

lazy val `embedded-cassandra-extension-core` = (project in file("embedded-cassandra-extension-core"))
  .settings(
    name := "embedded-cassandra-extension-core",
    coreDependencies
  ).enablePlugins(Plugin)

lazy val `embedded-cassandra-extension-example` = (project in file("embedded-cassandra-extension-example"))
  .dependsOn(`embedded-cassandra-extension-core`  % "test->test; compile->compile")
  .settings(
    name := "embedded-cassandra-extension-example",
    examplePillarSettings,
    exampleDependencies
  ).enablePlugins(Plugin)


