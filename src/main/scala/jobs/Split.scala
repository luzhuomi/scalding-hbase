package jobs

import com.twitter.scalding._
import cascading.tuple.Fields
import com.twitter.scalding.Args
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import com.twitter.scalding.Conversions._

/*
  create 't1', 'cf'
  create 't2', 'cf'
  create 't3', 'cf'
  put 't1','1','cf:c1', 'x'
  put 't1','2','cf:c1', 'yy'
*/

class Split(args: Args) extends Job(args) {
  val t1 = new HBaseSource("t1", "localhost", 'rid1, Array("cf"), Array('c1))
  val t3 = new HBaseSource("t3", "localhost", 'rid1, Array("cf"), Array('c1))
  val t4 = new HBaseSource("t4", "localhost", 'rid1, Array("cf"), Array('c1))
  
  val p1 = t1.read
  
  p1.filter('c1) { x : ImmutableBytesWritable => 
    (ibwToString(x).length > 1) }
  .write(t3)
  
  
  p1.filter('c1) { x : ImmutableBytesWritable => 
    !(ibwToString(x).length > 1) }
  .write(t4)
    
}
