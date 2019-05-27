package com.myorg.cassandra.extension.core.database.external

import com.datastax.driver.core.Session
import com.myorg.cassandra.extension.core.database.AbstractCassandraUnitSpec
import com.myorg.cassandra.extension.core.database.external.helper.ExternalCassandraServerHelper
import org.junit.{After, Before}

class IdamExternalCassandraSpec(loadDataSetLocation: String, unloadDataSetLocation: String, keySpaceName: String)
  extends AbstractCassandraUnitSpec(loadDataSetLocation, unloadDataSetLocation, keySpaceName) {

  override def keyspaceCreation: Boolean = false
  override def keyspaceDeletion: Boolean = false

  @Before
  @throws[Exception]
  override def before(): Unit = {
    new ExternalCassandraCQLUnit(getSession, getLoadDataSet, keySpaceName).execute()
  }

  @After override def after(): Unit = {
    new ExternalCassandraCQLUnit(getSession, getUnloadDataSet, keySpaceName).execute()
  }

  override protected def getSession: Session = ExternalCassandraServerHelper.session

}
