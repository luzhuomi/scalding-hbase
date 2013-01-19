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
  put 't1','1','cf:c1', '1'
  put 't2','1','cf:c2', '1'
*/

class SimpleJoin(args: Args) extends Job(args) {
  val t1 = new HBaseSource("t1", "localhost", 'rid1, Array("cf"), Array('c1))
  val t2 = new HBaseSource("t2", "localhost", 'rid2, Array("cf"), Array('c2))
  val t3 = new HBaseSource("t3", "localhost", 'rid1, Array("cf","cf"), Array('c1, 'c2))


  val p1 = t1.read
  .map('c1 -> 'log1) {x:ImmutableBytesWritable => 
                       print("c1 " + ibwToString(x))
		       x
		    }  
  
  val p2 = t2.read
  .map('c2 -> 'log2) {x:ImmutableBytesWritable => 
                       print("c2 " + ibwToString(x))
		       x
		    }  
  p2.joinWithSmaller('c2 -> 'c1, p1)
  .project('rid1 ,'c1, 'c2)
  .write(t3)
}

