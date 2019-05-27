package com.myorg.cassandra.extension.core.database.cache

import java.util.concurrent.ConcurrentHashMap

import com.datastax.driver.core.PreparedStatement
import com.google.inject.Singleton

import scala.collection.convert.Wrappers.JConcurrentMapWrapper

@Singleton
class PreparedStatementCacheServiceImpl extends IdamCacheService[String, PreparedStatement] {
  
      val preparedStatementMapCache : scala.collection.mutable.Map[String, PreparedStatement] =
      JConcurrentMapWrapper(new ConcurrentHashMap[String, PreparedStatement])
  
      override def add(key: String, entity: PreparedStatement): Unit = {
        if(! (preparedStatementMapCache exists(_ == key -> entity))){
            preparedStatementMapCache += (key -> entity)
          }
      }
  
      override def addAll(cacheMap: Map[String, PreparedStatement]): Unit = preparedStatementMapCache ++= cacheMap
  
      override def get(key: String): Option[PreparedStatement] = preparedStatementMapCache get key
  
      override def remove(key: String): Unit = preparedStatementMapCache -= key
  
      override def removeAll(): Unit = preparedStatementMapCache.clear()
  }
