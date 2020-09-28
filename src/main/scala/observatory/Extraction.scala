package observatory

import java.time.LocalDate
import scala.io.Source
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD

/**
  * 1st milestone: data extraction
  */
object Extraction extends ExtractionInterface {

  val spark: SparkSession =
    SparkSession
      .builder()
      .appName("Observatory")
      .master("local")
      .getOrCreate()
  
  // For implicit conversions like converting RDDs to DataFrames
  import spark.implicits._

  /**
    * @param year             Year number
    * @param stationsFile     Path of the stations resource file to use (e.g. "/stations.csv")
    * @param temperaturesFile Path of the temperatures resource file to use (e.g. "/1975.csv")
    * @return A sequence containing triplets (date, location, temperature)
    */
  def locateTemperatures(year: Year, stationsFile: String, temperaturesFile: String): Iterable[(LocalDate, Location, Temperature)] = {
    val stationsDf = getStationsDf(stationsFile)
      .where(column("Latitude").isNotNull && column("Longitude").isNotNull)
    val temperaturesDf = getTemperaturesDf(temperaturesFile)
    //stationsDf.show(20)
    //temperaturesDf.show(20)
    val joinedDf = stationsDf
      .join(temperaturesDf)
      .where(stationsDf("STN") <=> temperaturesDf("STN"))
      .where(stationsDf("WBAN") <=> temperaturesDf("WBAN"))
      .select(column("Month"), column("Day"), column("Latitude"), column("Longitude"), column("Temperature"))
      .where($"Temperature" < 9999)
    joinedDf.show(20)
    joinedDf.rdd.map(row => (
      LocalDate.of(year, row(0).asInstanceOf[Int], row(1).asInstanceOf[Int]),
      Location(row(2).asInstanceOf[Double], row(3).asInstanceOf[Double]),
      getTemperatureInCelsius(row(4).asInstanceOf[Double])
    )).collect.toList
  }

  /**
    * @param records A sequence containing triplets (date, location, temperature)
    * @return A sequence containing, for each location, the average temperature over the year.
    */
  def locationYearlyAverageRecords(records: Iterable[(LocalDate, Location, Temperature)]): Iterable[(Location, Temperature)] = {
    sparkAverageRecords(spark.sparkContext.parallelize(records.toSeq)).collect().toSeq
  }

  private def getStationsDf(stationsFile: String) = {
    spark.read.schema(
      StructType(
        List(
          StructField("STN", StringType, true),
          StructField("WBAN", StringType, true),
          StructField("Latitude", DoubleType, true),
          StructField("Longitude", DoubleType, true)
        )
      )
    ).csv(getDSFromResource(stationsFile))
  }

  private def getTemperaturesDf(temperaturesFile: String) = {
    spark.read.schema(
      StructType(
        List(
          StructField("STN", StringType, true),
          StructField("WBAN", StringType, true),
          StructField("Month", IntegerType, true),
          StructField("Day", IntegerType, true),
          StructField("Temperature", DoubleType, true)
        )
      )
    ).csv(getDSFromResource(temperaturesFile))
  }

  private def getDSFromResource(resource: String) = {
    //Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    val fileStream = Source.getClass.getResourceAsStream(resource)
    spark.sparkContext.makeRDD(Source.fromInputStream(fileStream).getLines().toList).toDS
  }

  private def getTemperatureInCelsius(temperature: Temperature): Temperature = {
    (temperature - 32.0) * 5.0 / 9.0
  }

  private def sparkAverageRecords(
    records: RDD[(LocalDate, Location, Temperature)]
  ): RDD[(Location, Temperature)] = {
    records.map {
      case(_, location, temperature) => (location, temperature)
    }.mapValues((_, 1))
     .reduceByKey {
       case(v1, v2) => (v1._1 + v2._1, v1._2 + v2._2)
     }
     .mapValues {
       case(sum, count) => sum / count
     }
  }
}
