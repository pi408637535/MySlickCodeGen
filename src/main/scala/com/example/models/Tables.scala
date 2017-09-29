package com.example.models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = scala.slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._
  import scala.slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import scala.slick.jdbc.{GetResult => GR}
  
  /** DDL for all tables. Call .create to execute. */
  lazy val ddl = Record.ddl ++ User.ddl ++ Wallet.ddl
  
  /** Entity class storing rows of table Record
   *  @param serno Database column serno DBType(BIGINT), AutoInc, PrimaryKey
   *  @param userid Database column userid DBType(VARCHAR), Length(50,true)
   *  @param `type` Database column type DBType(INT)
   *  @param amount Database column amount DBType(INT), Default(None)
   *  @param timecreate Database column timecreate DBType(DATETIME) */
  case class RecordRow(serno: Long, userid: String, `type`: Int, amount: Option[Int] = None, timecreate: java.sql.Timestamp)
  /** GetResult implicit for fetching RecordRow objects using plain SQL queries */
  implicit def GetResultRecordRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Int], e3: GR[Option[Int]], e4: GR[java.sql.Timestamp]): GR[RecordRow] = GR{
    prs => import prs._
    RecordRow.tupled((<<[Long], <<[String], <<[Int], <<?[Int], <<[java.sql.Timestamp]))
  }
  /** Table description of table record. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class Record(_tableTag: Tag) extends Table[RecordRow](_tableTag, "record") {
    def * = (serno, userid, `type`, amount, timecreate) <> (RecordRow.tupled, RecordRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (serno.?, userid.?, `type`.?, amount, timecreate.?).shaped.<>({r=>import r._; _1.map(_=> RecordRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column serno DBType(BIGINT), AutoInc, PrimaryKey */
    val serno: Column[Long] = column[Long]("serno", O.AutoInc, O.PrimaryKey)
    /** Database column userid DBType(VARCHAR), Length(50,true) */
    val userid: Column[String] = column[String]("userid", O.Length(50,varying=true))
    /** Database column type DBType(INT)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Column[Int] = column[Int]("type")
    /** Database column amount DBType(INT), Default(None) */
    val amount: Column[Option[Int]] = column[Option[Int]]("amount", O.Default(None))
    /** Database column timecreate DBType(DATETIME) */
    val timecreate: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("timecreate")
    
    /** Foreign key referencing Wallet (database name record_ibfk_1) */
    lazy val walletFk = foreignKey("record_ibfk_1", userid, Wallet)(r => r.userid, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Record */
  lazy val Record = new TableQuery(tag => new Record(tag))
  
  /** Entity class storing rows of table User
   *  @param id Database column id DBType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(100,true)
   *  @param password Database column password DBType(CHAR), Length(50,false)
   *  @param email Database column email DBType(VARCHAR), Length(100,true), Default(Some())
   *  @param phone Database column phone DBType(CHAR), Length(11,false), Default(Some())
   *  @param `type` Database column type DBType(TINYINT UNSIGNED), Default(Some(0))
   *  @param status Database column status DBType(TINYINT), Default(Some(false))
   *  @param initTime Database column init_time DBType(INT UNSIGNED), Default(Some(0))
   *  @param updateTime Database column update_time DBType(INT UNSIGNED), Default(Some(0))
   *  @param tombstone Database column tombstone DBType(TINYINT UNSIGNED), Default(Some(0)) */
  case class UserRow(id: Long, name: String, password: String, email: Option[String] = Some(""), phone: Option[String] = Some(""), `type`: Option[Byte] = Some(0), status: Option[Boolean] = Some(false), initTime: Option[Long] = Some(0L), updateTime: Option[Long] = Some(0L), tombstone: Option[Byte] = Some(0))
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[Byte]], e4: GR[Option[Boolean]], e5: GR[Option[Long]]): GR[UserRow] = GR{
    prs => import prs._
    UserRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<?[String], <<?[Byte], <<?[Boolean], <<?[Long], <<?[Long], <<?[Byte]))
  }
  /** Table description of table user. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class User(_tableTag: Tag) extends Table[UserRow](_tableTag, "user") {
    def * = (id, name, password, email, phone, `type`, status, initTime, updateTime, tombstone) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name.?, password.?, email, phone, `type`, status, initTime, updateTime, tombstone).shaped.<>({r=>import r._; _1.map(_=> UserRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7, _8, _9, _10)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Column[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(100,true) */
    val name: Column[String] = column[String]("name", O.Length(100,varying=true))
    /** Database column password DBType(CHAR), Length(50,false) */
    val password: Column[String] = column[String]("password", O.Length(50,varying=false))
    /** Database column email DBType(VARCHAR), Length(100,true), Default(Some()) */
    val email: Column[Option[String]] = column[Option[String]]("email", O.Length(100,varying=true), O.Default(Some("")))
    /** Database column phone DBType(CHAR), Length(11,false), Default(Some()) */
    val phone: Column[Option[String]] = column[Option[String]]("phone", O.Length(11,varying=false), O.Default(Some("")))
    /** Database column type DBType(TINYINT UNSIGNED), Default(Some(0))
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Column[Option[Byte]] = column[Option[Byte]]("type", O.Default(Some(0)))
    /** Database column status DBType(TINYINT), Default(Some(false)) */
    val status: Column[Option[Boolean]] = column[Option[Boolean]]("status", O.Default(Some(false)))
    /** Database column init_time DBType(INT UNSIGNED), Default(Some(0)) */
    val initTime: Column[Option[Long]] = column[Option[Long]]("init_time", O.Default(Some(0L)))
    /** Database column update_time DBType(INT UNSIGNED), Default(Some(0)) */
    val updateTime: Column[Option[Long]] = column[Option[Long]]("update_time", O.Default(Some(0L)))
    /** Database column tombstone DBType(TINYINT UNSIGNED), Default(Some(0)) */
    val tombstone: Column[Option[Byte]] = column[Option[Byte]]("tombstone", O.Default(Some(0)))
    
    /** Index over (email) (database name email) */
    val index1 = index("email", email)
    /** Index over (name) (database name name) */
    val index2 = index("name", name)
    /** Index over (phone) (database name phone) */
    val index3 = index("phone", phone)
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))
  
  /** Entity class storing rows of table Wallet
   *  @param userid Database column userid DBType(VARCHAR), Length(50,true)
   *  @param amount Database column amount DBType(INT) */
  case class WalletRow(userid: String, amount: Int)
  /** GetResult implicit for fetching WalletRow objects using plain SQL queries */
  implicit def GetResultWalletRow(implicit e0: GR[String], e1: GR[Int]): GR[WalletRow] = GR{
    prs => import prs._
    WalletRow.tupled((<<[String], <<[Int]))
  }
  /** Table description of table wallet. Objects of this class serve as prototypes for rows in queries. */
  class Wallet(_tableTag: Tag) extends Table[WalletRow](_tableTag, "wallet") {
    def * = (userid, amount) <> (WalletRow.tupled, WalletRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (userid.?, amount.?).shaped.<>({r=>import r._; _1.map(_=> WalletRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column userid DBType(VARCHAR), Length(50,true) */
    val userid: Column[String] = column[String]("userid", O.Length(50,varying=true))
    /** Database column amount DBType(INT) */
    val amount: Column[Int] = column[Int]("amount")
    
    /** Index over (userid) (database name userid) */
    val index1 = index("userid", userid)
  }
  /** Collection-like TableQuery object for table Wallet */
  lazy val Wallet = new TableQuery(tag => new Wallet(tag))
}