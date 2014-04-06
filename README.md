scalding-hbase
==============

The sample template for starting a scalding-hbase project. 
Mostly inherit from https://github.com/kianwilcox/hbase-scalding. 
Fixing all the compatibility issues with the newer version of scalding and maple. 
One of the issues overcomed is to allowed simple join among sources which was failing 
due to the use of Array[Byte] instead of ImmutableBytesWritable as mentioned in this post 
https://groups.google.com/forum/?fromgroups#!topic/cascading-user/ogrvoYbwtgg

    sbt update
  
    sbt assembly

    hadoop jar target/hbase-scalding-assembly-0.1.0.jar jobs.HBaseTests --hdfs --host localhost

