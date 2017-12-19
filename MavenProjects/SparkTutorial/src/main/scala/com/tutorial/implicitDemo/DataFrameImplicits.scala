package com.tutorial.implicitDemo


object DataFrameImplicits {

  import org.apache.spark.sql.{DataFrame, Column}
  import scala.util.{Try, Success, Failure}
  import org.apache.spark.sql.functions._


  implicit class ONSDataFrame(df: DataFrame) extends HiveTrait with DebugTrait{


    def checkColNames(columns : Seq[String]) : DataFrame  = {

      val colsFound = columns.flatMap(col => Try(df(col)).toOption)

      val okToContinue = columns.size == colsFound.size

      if(!okToContinue) throw ONSRuntimeException("Missing Columns Detected") else df
    }

    def addConstantColumn(cName : String, cVal: String) : DataFrame = {

        df.withColumn(cName, lit(cVal))
    }

    def estimation(groupByCol: String, sumCol: String) : DataFrame = {

      df.groupBy(groupByCol)
        .sum(sumCol)

    }

    def saveToHive(fLoc : String, fName: String): DataFrame = {
      saveAsHiveTable(df, fLoc, fName)
    }

    def writeDFToConsole(): DataFrame = {
      writeToConsole(df)
    }
  }





}
