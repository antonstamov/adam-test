import org.apache.spark.{SparkConf, SparkContext}
import org.bdgenomics.adam.rdd.ADAMContext

object App  extends App {
  val sparkConf = new SparkConf()
    .setMaster("local[*]")
    .setAppName("spark-context")
    .set("spark.driver.host","node01.biodtlab.com")
    .set("spark.driver.port","35710")
    .set("spark.yarn.historyServer.address","http://node01.biodtlab.com:18088")
    // .set("spark.driver.port", "41420")
    // .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")

  val sc = new SparkContext(sparkConf)
  val rdd = sc.textFile("/home/biodt/activator/adam-test/LICENSE")
    rdd.collect().foreach(println)

//   val ac = new ADAMContext(sc)
//   // Load alignments from disk
//   val reads = ac.loadAlignments("/home/user/SRR062634_1.filt.fastq")
//   // Generate, count and sort 21-mers
//   val kmers = reads.flatMap { read =>
//     read.getSequence.sliding(21)//.map(k => (k, 1L))
//   }.groupBy(x=>x).map(x=> (x._1,x._2.size))//.reduceByKey((k1: Long, k2: Long) => k1 + k2)
//     /*.map(_.swap)
//     .sortBy(_._1)*///ByKey(ascending = false)
//   // Print the top 10 most common 21-mers
//   kmers.take(10).foreach(println)


/* Array(
    (spark.tachyonStore.folderName,spark-7480861a-5045-4a7b-b747-2fe21a66421e), 
    (spark.eventLog.enabled,true), 
    (spark.yarn.historyServer.address,http://node01.biodtlab.com:18088), 
    (spark.driver.host,node01.biodtlab.com), 
    (spark.repl.class.uri,http://192.168.0.170:35524), 
    (spark.app.name,Spark shell), 
    (spark.fileserver.uri,http://192.168.0.170:43993), 
    (spark.jars,""), 
    (spark.eventLog.dir,hdfs://node01.biodtlab.com:8020/user/spark/applicationHistory), 
    (spark.driver.port,35710), (spark.master,local[*])
)


*/
}
