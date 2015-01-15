package jobs

import com.twitter.scalding._
import scala.collection.Iterable
import scala.collection.immutable.List
import java.util.Iterator
import cascading.tuple.Fields
import com.twitter.scalding._
import com.twitter.scalding.Args
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import com.twitter.scalding.Conversions._
/**
The purpose of this class is to test HBase -> Scala -> HBase serialization/desiralization
*
create 'scalding_in','data'
put 'scalding_in', '1', 'data:c2', 'abc'
put 'scalding_in', '2', 'data:c2', 'abcd'
put 'scalding_in', '3', 'data:c2', 'abcde' 
*/

class Reverse(args: Args) extends Job(args) {
  val table = new HBaseSource("scalding_in", "localhost", 'rowid, Array("data"), Array('c2)) 
  
  val out = new HBaseSource("scalding_out", "localhost", 'rowid, Array("data","data"), Array('c2,'c3))
  
  val n = 3
  /*
   idea
   take all the rowkeys 
   pivot it to [rk1, rk2, ... rkn]
   union with [1, 2 , ... , n ]
   pivot it back
   */ 
  val running_num = table.read.limit(1).flatMap('rowid -> 'count) { x : ImmutableBytesWritable => (1 to n) }
  
  
  

  table.read
  .map('rowid -> 'rowid) {x : ImmutableBytesWritable => x }
  .map('c2 -> 'c2) {x : ImmutableBytesWritable => x }
  .map('c2 -> 'c3) {c2: ImmutableBytesWritable => stringToibw(ibwToString(c2).reverse)}
  .project('rowid,'c2, 'c3)
  .write(out)
}
