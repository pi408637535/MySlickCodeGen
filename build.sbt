val slickVersion = "3.1.1"


scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.slick" % "slick_2.11" % "2.1.0",
  "com.typesafe.slick" % "slick-codegen_2.11" % "2.1.0",
  "org.mariadb.jdbc" % "mariadb-java-client" % "1.1.7"
)

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.8"


