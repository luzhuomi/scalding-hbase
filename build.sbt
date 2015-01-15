import AssemblyKeys._

// major sbt clean up done by https://github.com/ans-4175

name := "scalding-hbase"

organization := "com.github.luzhuomi"

version := "0.12.0"

//scalaVersion := "2.11.2" 

scalaVersion := "2.10.4"


resolvers ++= Seq( 
	  "Apache Repo" at "https://repository.apache.org/content/repositories/releases", 
	  "Thrift-Repo" at "http://people.apache.org/~rawson/repo", 
	  "Sonatype OSS Repo" at "https://oss.sonatype.org/content/repositories/releases", 
	  "Twitter Maven" at "http://maven.twttr.com", 
	  "MVN Repo" at "http://mvnrepository.com/artifact", 
	  "Concurrent Maven Repo" at "http://conjars.org/repo", 
	  "releases" at "http://scala-tools.org/repo-releases" 
) 

// Hbase 0.94 / Hadoop 2.2.0 

libraryDependencies ++= Seq( 
		    //"org.apache.zookeeper" % "zookeeper" % "3.4.6", 
		    //"org.specs2" %% "specs2" % "2.4.5" % "test", 
		    "org.apache.hadoop" % "hadoop-core" % "1.2.1", 
		    "org.apache.hbase" % "hbase" % "0.94.16", 
		    "cascading" % "cascading-core" % "2.1.6", 
		    "cascading" % "cascading-local" % "2.1.6", 
		    "cascading" % "cascading-hadoop" % "2.1.6", 
		    "com.twitter" %% "scalding-args" % "0.12.0", 
		    "com.twitter" %% "scalding-core" % "0.12.0", 
		    "com.twitter" %% "scalding-date" % "0.12.0" 
) 


mainClass := Some("com.twitter.scalding.Tool")

parallelExecution in Test := false


seq(assemblySettings: _*)


mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("log4j.properties") => MergeStrategy.discard
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case _ => MergeStrategy.last // leiningen build files
  }
}


// Uncomment to use Akka 
//libraryDependencies += "com.typesafe.akka" % "akka-actor" % "2.3.3" 
//libraryDependencies ~= { _.map(_.exclude("org.slf4j", "slf4j-log4j12")) } 