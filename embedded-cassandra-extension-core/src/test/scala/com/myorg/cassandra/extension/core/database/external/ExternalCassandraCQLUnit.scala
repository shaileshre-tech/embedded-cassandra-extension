package com.myorg.cassandra.extension.core.database.external

import com.datastax.driver.core.Session
import org.cassandraunit.dataset.CQLDataSet
import org.cassandraunit.utils.CqlOperations.use
import org.cassandraunit.{CQLDataLoader, CassandraCQLUnit}

class ExternalCassandraCQLUnit(session: Session, dataSet: CQLDataSet, keySpaceName: String) extends CassandraCQLUnit(dataSet){

  def execute() {
    load
  }

  override def load(): Unit = {
    use(session).accept(keySpaceName)

    val dataLoader = new CQLDataLoader(session)
    dataLoader.load(dataSet)
  }
}
