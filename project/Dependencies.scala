import sbt.Keys._
import sbt._

object Dependencies {

  lazy val scala_version = "2.11"
  lazy val play_version = "2.4.2"
  lazy val java_mail_version = "1.4.7"
  lazy val cassandra_java_driver_version = "3.4.0"
  lazy val guice_version = "4.1.0"

  lazy val scala_test_version = "3.0.5"
  lazy val cassandra_unit_version = "3.3.0.2"
  lazy val mockito_version = "2.10.0"

  object Compile {

    val play = "com.typesafe.play" % ("play_" + scala_version) % play_version
    val cassandraJavaDriver = "com.datastax.cassandra" % "cassandra-driver-core" % cassandra_java_driver_version
    val javaMail = "javax.mail" % "mail" % java_mail_version
    val guice = "com.google.inject" % "guice" % guice_version
  }

  object Test {

    val scalaTest = "org.scalatest" % ("scalatest_" + scala_version) % scala_test_version % "test"
    val mockito = "org.mockito" % "mockito-core" % mockito_version % "test"
    val playTest = "com.typesafe.play" % ("play-test_" + scala_version) % play_version % "test"
    val cassandraUnit = "org.cassandraunit" % "cassandra-unit" % cassandra_unit_version % "test"
  }

  import Compile._

  val l = libraryDependencies

  val coreDependencies = l ++= Seq(javaMail, cassandraJavaDriver, guice,
    Test.scalaTest, Test.mockito, Test.cassandraUnit)

  val exampleDependencies = l ++= Seq(play, cassandraJavaDriver, guice,
    Test.scalaTest, Test.mockito, Test.cassandraUnit, Test.playTest)

}
