package com.myorg.cassandra.extension.core.database.cache

trait IdamCacheService[A, B] {
  
      def add(key: A, entity : B): Unit
    def addAll(cacheMap: Map[A, B]): Unit
    def get(key: A) : Option[B]
    def remove(key: A): Unit
    def removeAll(): Unit
  }
