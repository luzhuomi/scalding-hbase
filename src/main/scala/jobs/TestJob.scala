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
/**
The purpose of this class is to test HBase -> Scala -> HBase serialization/desiralization
*/

class HBaseTests(args: Args) extends Job(args) {
  val table = new HBaseSource("test", "localhost", 'c1, Array("data"), Array('c2))
  
  val out = new HBaseSource("scalding_out", "localhost", 'c1, Array("data"), Array('c2))
  table.read
  .write(out)
}
