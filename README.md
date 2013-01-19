scalding-hbase
==============

The sample template for starting a scalding-hbase project. 
Mostly inherit from https://github.com/kianwilcox/hbase-scalding. 
Fixing all the compatibility issues with the newer version of scalding and maple.

sbt update
sbt assembly

hadoop jar target/hbase-scalding-assembly-0.1.0.jar jobs.HBaseTests --hdfs --host localhost

