package com.myorg.cassandra.extension.example.database.repository

import com.datastax.driver.core.BoundStatement
import com.google.inject.{Inject, Singleton}
import com.myorg.cassandra.extension.core.database.AbstractIdamCassandraRepository
import com.myorg.cassandra.extension.core.database.mapper.ParameterMapper
import com.myorg.cassandra.extension.core.database.template.IdamCassandraOperations
import com.myorg.cassandra.extension.example.database.entity.MyTable

@Singleton
class MyTableRepositoryImpl @Inject() (template: IdamCassandraOperations)
  extends AbstractIdamCassandraRepository(template) with MyTableRepository {

  private[this] val FIND_ALL_QUERY = "select id, value from mytable"
  private[this] val FIND_BY_ID = "select id, value from mytable where id = ?"

  override def findAll: List[MyTable] = {
    template.find(FIND_ALL_QUERY, noParameterMapper, rowMapperAll)
  }

  override def findById(id: String): MyTable = {
    template.find(FIND_BY_ID,
      new ParameterMapper {
        override def mapQueryParameters(boundStatement: BoundStatement): Unit = boundStatement.setString("id", id)
      },
      rowMapperAll).head
  }
}
