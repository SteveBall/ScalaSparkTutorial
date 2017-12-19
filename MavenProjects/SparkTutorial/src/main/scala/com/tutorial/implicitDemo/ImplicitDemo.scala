package com.tutorial.implicitDemo

import com.tutorial.utils.SparkCommon
import org.apache.spark.sql.{Column, DataFrame, SQLContext}
import com.typesafe.scalalogging.LazyLogging

object ImplicitDemo extends LazyLogging {

  val comp_id = "comp_id"
  val comp_name = "comp_name"
  val sector = "sector"
  val turnover = "turnover"
  val employee_count = "employee_count"

  val sqlContext: SQLContext = SparkCommon.sparkSQLContext

  sqlContext.setConf("spark.sql.shuffle.partitions", "2")

  @throws(classOf[ONSRuntimeException])
  def runVATProcess(dfIn : DataFrame) : DataFrame = {

    import VATImplicits._

    val vatColumns = Seq(sector, employee_count)

    dfIn.checkColNames(vatColumns)                             // common name checking
        .estimation(vatColumns.head, vatColumns(1))            // estimation - vat specific
        .saveToCSV(VATImplicits.vat, "Est_VAT_Processed")      // save to csv (Trait) - vat specific
        .runVATProcessing()                                    // any further vat processing
        .saveToHive(VATImplicits.vat, "Full_VAT_Processed")    // common save to Hive (Trait)

  }

  @throws(classOf[ONSRuntimeException])
  def runRSIProcess(dfIn : DataFrame) : DataFrame = {

    import RSIImplicits._

    val rsiColumns = Seq(sector, turnover)

    dfIn.checkColNames(rsiColumns)                             // common name checking
        .estimation(rsiColumns.head, rsiColumns(1))            // estimation - base class
        .runRSIProcessing()                                    // any further rsi processing
        .saveToHive(RSIImplicits.rsi ,"Full_RSI_Processed")    // common save to Hive (Trait)

  }

  @throws(classOf[ONSRuntimeException])
  def runRSIProcess_debug(dfIn : DataFrame) : DataFrame = {

    import RSIImplicits._

    val rsiColumns = Seq(sector, turnover)

    dfIn.checkColNames(rsiColumns)                                   // common name checking
        .estimation(rsiColumns.head, rsiColumns(1))                  // estimation - base class
        .writeDFToConsole()
        .runRSIProcessing()                                          // any further rsi processing
        .writeDFToConsole()
        .saveToHive(RSIImplicits.rsi ,"Debug_Full_RSI_Processed")    // common save to Hive (Trait)

  }

  def getDataFrame() : DataFrame = {

    sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("mode", "DROPMALFORMED")
      .option("inferSchema", "true")
      .load("src/main/resources/implicitsTest/Company.csv")

  }



  def main(args: Array[String]) {


    val vat_df = getDataFrame()
    val rsi_df = getDataFrame()


    try {

      runVATProcess(vat_df)

      runRSIProcess(rsi_df)

      runRSIProcess_debug(rsi_df)


    }
    catch {
      //case onsEx: ONSRuntimeException => println("******* ONS exception caught: " + onsEx)
      case onsEx: ONSRuntimeException => logger.debug("******* ONS exception caught: " + onsEx)

    }
  }
}
