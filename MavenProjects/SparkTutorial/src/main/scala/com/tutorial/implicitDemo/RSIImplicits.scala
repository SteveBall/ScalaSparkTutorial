package com.tutorial.implicitDemo

import com.tutorial.implicitDemo.DataFrameImplicits.ONSDataFrame
import org.apache.spark.sql.DataFrame

object RSIImplicits {

  val rsi = "RSI"

  implicit class RSIDataFrame(df: DataFrame) extends ONSDataFrame(df: DataFrame) {


    def runRSIProcessing() : DataFrame = {

      val rsiCol = "rsi_column"

      addConstantColumn(rsiCol, rsi)

    }
  }
}
