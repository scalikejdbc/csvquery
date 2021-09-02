lazy val root = (project in file("."))
  .settings(
    organization := "org.scalikejdbc",
    name := "csvquery",
    version := "1.5.1-SNAPSHOT",
    scalaVersion := "2.13.6",
    crossScalaVersions := Seq("2.12.14", "2.13.6", "3.0.2"),
    libraryDependencies ++= Seq(
      "com.h2database"       %  "h2"              % "1.4.200",
      "org.scalikejdbc"      %% "scalikejdbc"     % "4.0.0-RC2",
      "ch.qos.logback"       %  "logback-classic" % "1.2.5"     % "provided",
      "org.scalatest"        %% "scalatest"       % "3.2.9"     % "test"
    ),
    Test / parallelExecution := false,
    Test / logBuffered := false,
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
    initialCommands := """
import scalikejdbc._
import csvquery._

Class.forName("org.h2.Driver")

implicit val session: DBSession = autoCSVSession

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
""",
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (version.value.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
      else Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    publishMavenStyle := true,
    pomIncludeRepository := { x => false },
    pomExtra := <url>https://github.com/scalikejdbc/csvquery/</url>
  <licenses>
    <license>
      <name>MIT License</name>
      <url>https://www.opensource.org/licenses/mit-license</url>
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
      <url>https://git.io/sera</url>
    </developer>
  </developers>
  )
