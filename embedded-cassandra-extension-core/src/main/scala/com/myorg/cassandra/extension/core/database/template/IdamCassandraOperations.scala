package com.myorg.cassandra.extension.core.database.template

import com.datastax.driver.core.{BoundStatement, PreparedStatement, ResultSet, Session}
import com.myorg.cassandra.extension.core.database.cache.IdamCacheService
import com.myorg.cassandra.extension.core.database.mapper.{ParameterMapper, RowMapper}

trait IdamCassandraOperations {
  
  def find [T] (query: String, parameterMapper: ParameterMapper, rowMapper : RowMapper[T]) : List[T]
  def update (query: String, parameterMapper: ParameterMapper) : Int

  protected def prepareAndExecute(session: Session, cacheService: IdamCacheService[String, PreparedStatement],
                                  query: String, parameterMapper: ParameterMapper): ResultSet = {

      val preparedStatementOption: Option[PreparedStatement] = cacheService get query

      val preparedStatement: PreparedStatement = preparedStatementOption getOrElse({
        val ps = session.prepare(query)
        cacheService add (query, ps)
        ps
      })

      val boundStatement: BoundStatement = preparedStatement bind

      parameterMapper.mapQueryParameters(boundStatement)

      session.execute(boundStatement)
  }
}
