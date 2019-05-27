package com.myorg.cassandra.extension.core.database

import com.datastax.driver.core.{Cluster, Session}

object CassandraConnection {

  val cluster = Cluster.builder.addContactPoint("127.0.0.1").build
  val session = cluster.connect("idam")

  def getSession : Session = session

  Runtime.getRuntime.addShutdownHook(new Thread(new Runnable {
    override def run(): Unit = {
      session.close()
      cluster.close()
    }
  }))
}
