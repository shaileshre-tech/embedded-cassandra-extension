package com.myorg.cassandra.extension.core.database

import org.cassandraunit.AbstractCassandraUnit4CQLTestCase
import org.cassandraunit.dataset.CQLDataSet
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet

abstract class AbstractCassandraUnitSpec(loadDataSetLocation: String, unloadDataSetLocation: String, keySpaceName: String)
  extends AbstractCassandraUnit4CQLTestCase {

  protected def keyspaceCreation: Boolean = true
  protected def keyspaceDeletion: Boolean = true

  override def getDataSet: CQLDataSet = {
    new ClassPathCQLDataSet(loadDataSetLocation, keyspaceCreation, keyspaceCreation, keySpaceName)
  }

  def getLoadDataSet: CQLDataSet = {
    getDataSet
  }

  def getUnloadDataSet: CQLDataSet = {
    new ClassPathCQLDataSet(unloadDataSetLocation, keyspaceCreation, keyspaceCreation, keySpaceName)
  }
}
