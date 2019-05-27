package com.myorg.cassandra.extension.core.database

import com.datastax.driver.core.BoundStatement
import com.myorg.cassandra.extension.core.database.mapper.ParameterMapper
import com.myorg.cassandra.extension.core.database.template.IdamCassandraOperations

abstract class AbstractIdamCassandraRepository(template: IdamCassandraOperations) {

  protected val noParameterMapper = new ParameterMapper {
    override def mapQueryParameters(boundStatement: BoundStatement): Unit = {}
  }
}
