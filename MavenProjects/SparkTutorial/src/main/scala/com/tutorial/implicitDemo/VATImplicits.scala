package com.tutorial.implicitDemo

import com.tutorial.implicitDemo.DataFrameImplicits.ONSDataFrame
import org.apache.spark.sql.DataFrame


object VATImplicits {

  val vat = "VAT"

  implicit class VATDataFrame(df: DataFrame) extends ONSDataFrame(df: DataFrame) with CSVTrait {

    def runVATProcessing() : DataFrame = {

      val vatCol = "vat_column"

      addConstantColumn(vatCol, vat)

    }

    override def estimation(groupByCol: String, maxCol: String) : DataFrame = {

      df.groupBy(groupByCol)
        .max(maxCol)

    }

    def saveToCSV(fLoc : String, fName: String): DataFrame = {
      saveAsCSV(df, fLoc, fName)
    }
  }
}
