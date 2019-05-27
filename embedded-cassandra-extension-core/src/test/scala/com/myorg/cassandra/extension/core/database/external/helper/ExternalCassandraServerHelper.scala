package com.myorg.cassandra.extension.core.database.external.helper

import com.datastax.driver.core.{Cluster, QueryOptions}

class ExternalCassandraServerHelper(cassandraHost: String = "127.0.0.1", cassandraPort: Int = 9042,
                                    clusterName: String = "Test Cluster", keySpace: String = "idam") {

  def queryOptions = {
    val queryOptions = new QueryOptions

    queryOptions.setRefreshSchemaIntervalMillis(0)
    queryOptions.setRefreshNodeIntervalMillis(0)
    queryOptions.setRefreshNodeListIntervalMillis(0)
  }

  val cluster: Cluster = {
    com.datastax.driver.core.Cluster.builder
      .addContactPoint(cassandraHost)
      .withPort(cassandraPort)
      .withQueryOptions(queryOptions)
      .withClusterName(clusterName)
      .build
  }

  val session = cluster.connect
}

object ExternalCassandraServerHelper {
  val externalServerHelper = new ExternalCassandraServerHelper

  val session = {
    Runtime.getRuntime.addShutdownHook(new Thread(new Runnable {
      override def run(): Unit = {
        externalServerHelper.session.close()
        externalServerHelper.cluster.close()
      }
    }))

    externalServerHelper.session
  }

}
