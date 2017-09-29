package com.sam.codegen

/**
  * Created by piguanghua on 7/7/17.
  */
import slick.codegen.SourceCodeGenerator


object SlickCodegen extends App{
  val slickDriver = "scala.slick.driver.MySQLDriver"
  val jdbcDriver = "org.mariadb.jdbc.Driver"
  val url = "jdbc:mysql://127.0.0.1:5306/scala_db?characterEncoding=UTF-8&useUnicode=true"
  val outputFolder = "src/main/scala"
  val pkg = "com.example.models"
  var user = "root"
  var password = "test@wode2017social"

  SourceCodeGenerator.main(
    Array(
      slickDriver,
      jdbcDriver,
      url,
      outputFolder,
      pkg,
      user,
      password
    )
  )

}
