package com.myorg.cassandra.extension.core.database.embedded

import com.datastax.driver.core.Session
import com.myorg.cassandra.extension.core.database.AbstractCassandraUnitSpec
import org.cassandraunit.utils.EmbeddedCassandraServerHelper

class IdamEmbeddedCassandraSpec(loadDataSetLocation: String, unloadDataSetLocation: String, keySpaceName: String)
  extends AbstractCassandraUnitSpec(loadDataSetLocation, unloadDataSetLocation, keySpaceName) {

  override def getSession: Session = EmbeddedCassandraServerHelper.getSession
}
