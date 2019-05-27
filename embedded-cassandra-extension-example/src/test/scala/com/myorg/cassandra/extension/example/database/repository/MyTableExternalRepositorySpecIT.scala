package com.myorg.cassandra.extension.example.database.repository

import com.myorg.cassandra.extension.core.database.template.IdamCassandraTemplate
import com.myorg.cassandra.extension.core.database.test.AbstractIdamDatabaseIntegrationSpec
import com.myorg.cassandra.extension.example.database.entity.MyTable

class MyTableExternalRepositorySpecIT extends AbstractIdamDatabaseIntegrationSpec[MyTableRepository] {

  override def initializeUnderTestRepository(template: IdamCassandraTemplate): MyTableRepository = new MyTableRepositoryImpl(template)

  override def keySpaceName: String = "pillar_test"

  override def loadDataSetLocation: String = "com/myorg/cassandra/extension/example/database/repository/external-simple-insert.cql"
  override def unloadDataSetLocation: String = "com/myorg/cassandra/extension/example/database/repository/external-simple-delete.cql"

  private val myTableListExpected = List (MyTable("myKey02", "myValue02"), MyTable("myKey01", "myValue01"))


  feature("the repository `MyTableRepository` should support various database operations") {

    scenario("the `findAll` api should fetch all records") {
      val myTableList = underTestRepository.findAll

      myTableList.size must equal(2)
      myTableList must equal(myTableListExpected)
    }

    scenario("the `findById` api should fetch only one record"){
      val myTable = underTestRepository.findById("myKey01")

      myTable must equal(myTableListExpected.tail.head)
    }
  }
}
