package com.myorg.cassandra.extension.core.database.mapper

import com.datastax.driver.core.BoundStatement

trait ParameterMapper {
  
      def mapQueryParameters(boundStatement: BoundStatement): Unit
  }