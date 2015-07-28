import sbt._

name := """appname"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "4.2.3.Final" ,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.18",
  javaWs,
  "net.sf.flexjson" % "flexjson" % "2.1",
  "com.amazonaws" % "aws-java-sdk" % "1.8.8",
  "org.apache.poi" % "poi" % "3.9"
)

javaOptions in Test ++= Seq(
  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=9998",
  "-Xms512M",
  "-Xmx1536M",
  "-Xss1M",
  "-XX:MaxPermSize=384M"
)

