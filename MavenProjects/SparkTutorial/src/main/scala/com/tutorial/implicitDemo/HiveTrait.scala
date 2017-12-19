package com.tutorial.implicitDemo

import org.apache.spark.sql.{DataFrame, SaveMode}

trait HiveTrait {

  /*
  val databaseName = "test_trade"

  def saveAsHiveTable(df: DataFrame, htName: String): DataFrame = {

    df.write.mode(SaveMode.Overwrite).saveAsTable(databaseName + "." + htName)

    df
  }

*/

  def saveAsHiveTable(df: DataFrame, fLoc : String, htName: String): DataFrame = {

    // We will just dummy this up as a CSV for now as I don't have Hadoop running
    df.write.format("com.databricks.spark.csv").option("header", "true")
      .save("src/main/resources/implicitsTest/outputs/" + fLoc + "/" + "Hive_" + htName + ".csv")

    df
  }

}
