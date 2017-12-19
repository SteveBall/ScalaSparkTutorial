package com.tutorial.implicitDemo

import org.apache.spark.sql.DataFrame

trait CSVTrait {

  val baseLocation = "src/main/resources/implicitsTest/outputs/"

  def saveAsCSV(df: DataFrame, fLoc : String, fName: String): DataFrame = {
    df.write.format("com.databricks.spark.csv").option("header", "true")
      .save(baseLocation + fLoc + "/" + fName + ".csv")

    df
  }
}
