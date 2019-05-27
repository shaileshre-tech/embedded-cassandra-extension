package com.myorg.cassandra.extension.core.database.mapper

import com.datastax.driver.core.Row

trait RowMapper[T] {
  
      def mapRow(row: Row) : T
  }
