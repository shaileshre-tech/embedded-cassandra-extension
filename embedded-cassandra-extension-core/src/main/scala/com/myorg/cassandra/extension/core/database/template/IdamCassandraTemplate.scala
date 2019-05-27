package com.myorg.cassandra.extension.core.database.template

import com.datastax.driver.core.{PreparedStatement, ResultSet, Session}
import com.google.inject.name.Named
import com.google.inject.{Inject, Singleton}
import com.myorg.cassandra.extension.core.database.cache.IdamCacheService
import com.myorg.cassandra.extension.core.database.mapper.{ParameterMapper, RowMapper}

import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

@Singleton
class IdamCassandraTemplate @Inject()
  (@Named("cassandraSession") val session: Session,
   @Named("preparedStatementCacheService") val cacheService: IdamCacheService[String, PreparedStatement])
  extends IdamCassandraOperations {

    override def find[T](query: String, parameterMapper: ParameterMapper, rowMapper: RowMapper[T]): List[T] = {
      val resultSet: ResultSet = prepareAndExecute(session, cacheService, query, parameterMapper)

      val listBuffer = new ListBuffer[T]

      if (! resultSet.isExhausted) {
        for (row <- resultSet.iterator()) (listBuffer += rowMapper.mapRow(row))
      }

      listBuffer.result()
    }

    override def update(query: String, parameterMapper: ParameterMapper): Int = {
      val resultSet: ResultSet = prepareAndExecute(session, cacheService, query, parameterMapper)
  
      if (! resultSet.isExhausted) {
        resultSet.getAvailableWithoutFetching
      } else {
        -1
      }
    }
  }