Embedded Cassandra Extension
=======================================

What is it ?
------------
Embedded cassandra extension is a Scala implementation to simplify development and testing activities using Cassandra DB.
It introduces a basic template based implementation which encapsulates the lower level operations [On lines of Spring jdbc].
It also introduces an extension to CassandraUnit [test framework] to add support for external cassandra DB.


Why the need ?
--------------
1. Cassandra database access needs lower level implementation.
2. CassandraUnit introduction leads to duplication of DDL statements [thus decreasing maintainability]
3. CassandraUnit creates the data structures during the test execution. This might lead in decreased performance.
4. CassandraUnit drops the complete key-space in order to perform cleanup. This might lead in decreased performance.


Main features :
---------------
1. Introduces templates, row and parameter mappers for simplifying data access implementation.
2. Supports interaction with any existing external cassandra server during test phase.
3. Supports easy switching between embedded and external cassandra server.
4. Supports data load and cleanup operations before and after test execution.


Examples :
----------
https://github.com/shaileshre/embedded-cassandra-extension/tree/master/embedded-cassandra-extension-example
