package com.example.models

import java.text.SimpleDateFormat
import java.sql.Timestamp
import java.util.Date

import scala.slick.lifted.TableQuery
import com.example.models.Tables._

import scala.concurrent.Await
import scala.slick.driver.MySQLDriver.simple._

/**
  * Created by piguanghua on 7/7/17.
  */
object HelloSlick extends App{
  //val members: TableQuery[Member] = TableQuery[Member]
  val sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  def ts(str: String) = new Timestamp(sdf.parse(str).getTime)

  Database.forURL("jdbc:mysql://118.244.212.178:5306/scala_db?characterEncoding=UTF-8&useUnicode=true",
    driver = "org.mariadb.jdbc.Driver",
    user="root",
    password="test@wode2017social").withSession {
    implicit session =>
      // <- write queries here
      Wallet foreach { case WalletRow(userid, amount) =>
        println(" " + userid + ":" + amount )
      }

      //新增一筆錢包用戶
     /* Wallet += WalletRow("100", 0)

      //批次新增多筆用戶
      Wallet ++= Seq(
        WalletRow("101", 5000),
        WalletRow("102", 300)
      )*/

      //新增一筆消費紀錄並回傳自動增長的序號
      val serno1 = (Record returning Record.map(_.serno)) += RecordRow(0, "100", 0, Option(100), new Timestamp(System.currentTimeMillis()))
      println("serno1 => " + serno1)

      //新增一筆特定欄位，其餘欄位為預設值，但是這邊會回傳的值為執行成功與否
      Record.map(m => (m.userid, m.`type`, m.amount, m.timecreate)) +=("100", 0, Option(110), new Timestamp(System.currentTimeMillis()))

      //新增指定欄位並回傳自動增長的序號
      val serno2 = (Record.map(m => (m.userid, m.`type`, m.amount, m.timecreate)) returning Record.map(_.serno)) +=("100", 0, Option(120), new Timestamp(System.currentTimeMillis()))
      println("serno2 => " + serno2)

      //可以觀察組合出來的SQL
      val statement = Record.insertStatement
      println(statement)
      //insert into `record` (`userid`,`type`,`amount`,`timecreate`)  values (?,?,?,?)

      val invoker = Record.insertInvoker
      println(invoker)
      //scala.slick.driver.JdbcInsertInvokerComponent$CountingInsertInvoker@7e5afaa6

      //搜尋並讀出每一筆資料的email

      val q = Record
        .filter(_.userid inSet Set("100", "101", "102"))
        .filter(_.amount === 100)
        .sortBy(_.timecreate.desc.nullsFirst)
      //println("q: " + q.selectStatement);
      for (r <- q.list) {
        println("Value of Amount: " + r.amount);
      }

      //取出第一個
      val r1 = q.first
      println(r1)
      //RecordRow(1,100,0,Some(100),2015-01-05 00:21:09.0)

      //使用Join方式搜尋
      val useridlist = List("100", "101", "102")
      val q2 = for {
        a <- Wallet
        b <- Record
        if a.userid === b.userid
        if a.userid inSet useridlist
      } yield (a.userid, b.amount, b.timecreate)
      //println("q2: " + q2.selectStatement)
      //select x2.`userid`, x3.`amount`, x3.`timecreate` from `wallet` x2, `record` x3 where x2.`userid` = x3.`userid`
      for (r <- q.list) {
        println("ID:" + r.userid + ",Amount:" + r.amount + ",Time:" + r.timecreate);
      }

      //更新部分先找出對象再執行更新
      val q3 = for {
        r <- Record
        if r.userid === "100"
        if r.serno === (3).toLong
      } yield (r.serno, r.`type`, r.amount)
      //println("q3: " + q3.updateStatement)
      //update `record` set `serno` = ?, `type` = ?, `amount` = ? where (`record`.`userid` = '100') and (`record`.`serno` = 3)
      q3.update((3).toLong, 0, Option(500))

      //將查詢方法的結果刪除
      q.delete
  }

}
