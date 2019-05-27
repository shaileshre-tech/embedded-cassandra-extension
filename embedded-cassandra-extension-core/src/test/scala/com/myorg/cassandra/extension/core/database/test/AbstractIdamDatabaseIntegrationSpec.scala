package com.myorg.cassandra.extension.core.database.test

import com.myorg.cassandra.extension.core.database.AbstractCassandraUnitSpec
import com.myorg.cassandra.extension.core.database.cache.PreparedStatementCacheServiceImpl
import com.myorg.cassandra.extension.core.database.embedded.IdamEmbeddedCassandraSpec
import com.myorg.cassandra.extension.core.database.external.IdamExternalCassandraSpec
import com.myorg.cassandra.extension.core.database.template.IdamCassandraTemplate

abstract class AbstractIdamDatabaseIntegrationSpec[T] extends AbstractIdamIntegrationSpec {

  private val supportedDatabaseScriptExtension = ".cql"
  private val defaultCassandraType = LegalCassandraType.EXTERNAL
  private val idamCassandraSpec = getIdamCassandraSpec()

  protected def cassandraType: LegalCassandraType.CassandraType = defaultCassandraType
  protected def keySpaceName: String = "idamTest"
  protected def loadDataSetLocation: String
  protected def unloadDataSetLocation: String

  protected var underTestRepository: T = _
  protected def initializeUnderTestRepository(template: IdamCassandraTemplate): T

  override def beforeAll(): Unit = {
    idamCassandraSpec.before

    underTestRepository = initializeUnderTestRepository(
      new IdamCassandraTemplate(idamCassandraSpec.getSession, new PreparedStatementCacheServiceImpl))
  }

  override def afterAll(): Unit = {
    idamCassandraSpec.after
  }

  private def validateDataSetExtension(dataSetLocation: String): String = {
    if(dataSetLocation endsWith(supportedDatabaseScriptExtension))
      dataSetLocation
    else
      throw new IllegalArgumentException(
        "Incorrect data set format found in 'dataSetLocation'. Legal format is '"+supportedDatabaseScriptExtension+"'")
  }

  private def getIdamCassandraSpec(): AbstractCassandraUnitSpec = {
    validateDataSetExtension(loadDataSetLocation)

    if(cassandraType == defaultCassandraType)
      new IdamExternalCassandraSpec(loadDataSetLocation, unloadDataSetLocation, keySpaceName)
    else
      new IdamEmbeddedCassandraSpec(loadDataSetLocation, unloadDataSetLocation, keySpaceName)
  }

  object LegalCassandraType {
    sealed trait CassandraType
    case object EMBEDDED extends CassandraType
    case object EXTERNAL extends CassandraType
  }
}
