lazy val root = (project in file("."))
  .settings(
    organization := "org.scalikejdbc",
    name := "csvquery",
    version := "0.3",
    scalaVersion := "2.11.5",
    crossScalaVersions := Seq("2.10.4", "2.11.5"),
    libraryDependencies ++= Seq(
      "com.h2database"       %  "h2"              % "1.4.+",
      "org.scalikejdbc"      %% "scalikejdbc"     % "2.2.+",
      "org.skinny-framework" %% "skinny-orm"      % "1.3.+"   % "provided",
      "ch.qos.logback"       %  "logback-classic" % "1.1.+"   % "provided",
      "org.skinny-framework" %  "skinny-logback"  % "1.0.+"   % "test",
      "org.scalatest"        %% "scalatest"       % "2.2.+"   % "test"
    ),
    parallelExecution in Test := false,
    logBuffered in Test := false,
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
    initialCommands := """
import scalikejdbc._
import csvquery._
implicit val session = autoCSVSession

val csv = CSV("./sample.csv", Seq("name", "age"))

val count = withCSV(csv) { table =>
  sql"select count(*) from $table".map(_.long(1)).single.apply().get
}

val records = withCSV(csv) { table =>
  sql"select * from $table".toMap.list.apply()
}

case class Account(name: String, companyName: String, company: Option[Company])
case class Company(name: String, url: String)

val (accountsCsv, companiesCsv) = (
  CSV("src/test/resources/accounts.csv", Seq("name", "company_name")),
  CSV("src/test/resources/companies.csv", Seq("name", "url"))
)
val accounts: Seq[Account] = withCSV(accountsCsv, companiesCsv) { (a, c) =>
  sql"select a.name, a.company_name, c.url from $a a left join $c c on a.company_name = c.name".map { rs =>
    new Account(
      name = rs.get("name"),
      companyName = rs.get("company_name"),
      company = rs.stringOpt("url").map(url => Company(rs.get("company_name"), url))
    )
  }.list.apply()
}

// NOTE: compilation on the REPL fails, use initialCommands instead.
case class User(name: String, age: Int)
object UserDAO extends SkinnyCSVMapper[User] {
  def csv = CSV("./sample.csv", Seq("name", "age"))
  override def extract(rs: WrappedResultSet, rn: ResultName[User]) = autoConstruct(rs, rn)
}
val users = UserDAO.findAll()
val alice = UserDAO.where('name -> "Alice").apply().headOption
""",
    publishTo <<= version { (v: String) => 
      val nexus = "https://oss.sonatype.org/"
      if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
      else Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    publishMavenStyle := true,
    pomIncludeRepository := { x => false },
    pomExtra := <url>https://github.com/scalikejdbc/csvquery/</url>
  <licenses>
    <license>
      <name>MIT License</name>
      <url>http://www.opensource.org/licenses/mit-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:scalikejdbc/csvquery.git</url>
    <connection>scm:git:git@github.com:scalikejdbc/csvquery.git</connection>
  </scm>
  <developers>
    <developer>
      <id>seratch</id>
      <name>Kazuhiro Sera</name>
      <url>http://git.io/sera</url>
    </developer>
  </developers>
  ).settings(scalariformSettings: _*)
   .settings(sonatypeSettings: _*)
   .settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)

