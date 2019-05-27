package com.myorg.cassandra.extension.example.database.repository

import com.datastax.driver.core.Row
import com.myorg.cassandra.extension.core.database.mapper.RowMapper
import com.myorg.cassandra.extension.example.database.entity.MyTable

trait MyTableRepository {

  def findAll : List[MyTable]
  def findById(id: String): MyTable

  protected val rowMapperAll = new RowMapper[MyTable] {
    override def mapRow(row: Row): MyTable = {
      MyTable(
        row.getString("id"),
        row.getString("value"))
    }
  }
}
