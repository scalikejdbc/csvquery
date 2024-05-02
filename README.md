# CSVQuery 

`CSVQuery` is a handy SQL runner to work with CSV files. You can use SQL to parse and filter CSV rows!

## Setup with [sbt](https://github.com/sbt/sbt)

```sh
echo 'scalaVersion := "2.13.14"

libraryDependencies += "org.scalikejdbc" %% "csvquery" % "1.5.0"' > build.sbt

echo "Alice,23
Bob,34
Chris,30" > sample.csv
```

And then try the folling things on `sbt console`.

```scala
import scalikejdbc._
import csvquery._
implicit val session: DBSession = autoCSVSession

// ---
// simple queries

val csv = CSV("./sample.csv", Seq("name", "age"))

val count: Long = withCSV(csv) { table =>
  sql"select count(*) from $table".map(_.long(1)).single.apply().get
}

val records: Seq[Map[String, Any]] = withCSV(csv) { table =>
  sql"select * from $table".toMap.list.apply()
}

// ---
// join queries

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
```

Output example:

```
scala> import scalikejdbc._
import scalikejdbc._

scala> import csvquery._
import csvquery._

scala> implicit val session: DBSession = autoCSVSession
13:14:11.078 [run-main-0] DEBUG scalikejdbc.ConnectionPool$ - Registered connection pool : ConnectionPool(url:jdbc:h2:mem:csvquery-1413951250977, user:) using factory : <default>
session: scalikejdbc.DBSession = NamedAutoSession(csvquery-1413951250977)

scala> val csv = CSV("./sample.csv", Seq("name", "age"))
csv: csvquery.CSV = CSV(./sample.csv,List(name, age),UTF-8)

scala> val count = withCSV(csv) { table =>
     |   sql"select count(*) from $table".map(_.long(1)).single.apply().get
     | }
13:14:15.815 [run-main-0] DEBUG s.StatementExecutor$$anon$1 - SQL execution completed

  [SQL Execution]
   select count(*) from csvread('./sample.csv', 'NAME,AGE', 'UTF-8'); (0 ms)

  [Stack Trace]
    ...
    $line8.$read$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$anonfun$1.apply(<console>:23)
    $line8.$read$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$anonfun$1.apply(<console>:22)
    csvquery.CSVQuery$.withCSV(CSVQuery.scala:15)
    csvquery.package$.withCSV(package.scala:9)
    $line8.$read$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$.<init>(<console>:22)
    $line8.$read$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$.<clinit>(<console>)
    $line8.$eval$.$print$lzycompute(<console>:7)
    $line8.$eval$.$print(<console>:6)
    $line8.$eval.$print(<console>)
    sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    java.lang.reflect.Method.invoke(Method.java:483)
    scala.tools.nsc.interpreter.IMain$ReadEvalPrint.call(IMain.scala:739)
    scala.tools.nsc.interpreter.IMain$Request.loadAndRun(IMain.scala:986)
    ...

count: Long = 3

scala> val records = withCSV(csv) { table =>
     |   sql"select * from $table".toMap.list.apply()
     | }
13:14:18.583 [run-main-0] DEBUG s.StatementExecutor$$anon$1 - SQL execution completed

  [SQL Execution]
   select * from csvread('./sample.csv', 'NAME,AGE', 'UTF-8'); (1 ms)

  [Stack Trace]
    ...
    $line9.$read$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$anonfun$1.apply(<console>:23)
    $line9.$read$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$anonfun$1.apply(<console>:22)
    csvquery.CSVQuery$.withCSV(CSVQuery.scala:15)
    csvquery.package$.withCSV(package.scala:9)
    $line9.$read$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$.<init>(<console>:22)
    $line9.$read$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$.<clinit>(<console>)
    $line9.$eval$.$print$lzycompute(<console>:7)
    $line9.$eval$.$print(<console>:6)
    $line9.$eval.$print(<console>)
    sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    java.lang.reflect.Method.invoke(Method.java:483)
    scala.tools.nsc.interpreter.IMain$ReadEvalPrint.call(IMain.scala:739)
    scala.tools.nsc.interpreter.IMain$Request.loadAndRun(IMain.scala:986)
    ...

records: List[Map[String,Any]] = List(Map(NAME -> Alice, AGE -> 23), Map(NAME -> Bob, AGE -> 34), Map(NAME -> Chris, AGE -> 30))

scala> val accounts: Seq[Account] = withCSV(accountsCsv, companiesCsv) { (a, c) =>
     |   sql"select a.name, a.company_name, c.url  from $a a left join $c  c on a.company_name = c.name".map { rs =>
     |     new Account(
     |       name = rs.get("name"),
     |       companyName = rs.get("company_name"),
     |       company = rs.stringOpt("url").map(url => Company(rs.get("company_name"), url))
     |     )
     |   }.list.apply()
     | }
13:14:18.590 [run-main-0] DEBUG s.StatementExecutor$$anon$1 - SQL execution completed

  [SQL Execution]
   select a.name, a.company_name, c.url from csvread('src/test/resources/accounts.csv', 'NAME,COMPANY_NAME', 'UTF-8') a left join csvread('src/test/resources/companies.csv', 'NAME,URL', 'UTF-8') c on a.company_name = c.name; (3 ms)

  [Stack Trace]
    ...
    $line4.$read$$iw$$iw$$iw$$iw$$iw$$iw$$anonfun$1.apply(<console>:27)
    $line4.$read$$iw$$iw$$iw$$iw$$iw$$iw$$anonfun$1.apply(<console>:20)
    csvquery.CSVQuery$.withCSV(CSVQuery.scala:17)
    csvquery.package$.withCSV(package.scala:11)
    $line4.$read$$iw$$iw$$iw$$iw$$iw$$iw$.<init>(<console>:20)
    $line4.$read$$iw$$iw$$iw$$iw$$iw$$iw$.<clinit>(<console>)
    $line4.$eval$.$print$lzycompute(<console>:7)
    $line4.$eval$.$print(<console>:6)
    $line4.$eval.$print(<console>)
    sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    java.lang.reflect.Method.invoke(Method.java:483)
    scala.tools.nsc.interpreter.IMain$ReadEvalPrint.call(IMain.scala:739)
    scala.tools.nsc.interpreter.IMain$Request.loadAndRun(IMain.scala:986)
    ...

accounts: Seq[Account] = List(Account(Alice,Oracle,Some(Company(Oracle,http://www.oracle.com/index.html))), Account(Bob,Google,Some(Company(Google,https://www.google.com/))), Account(Chris,Google,Some(Company(Google,https://www.google.com/))), Account(Denis,Microsoft,None), Account(Eric,Red Hat,Some(Company(Red Hat,http://www.redhat.com/en))), Account(Fred,Facebook,Some(Company(Facebook,https://www.facebook.com/))), Account(George,Google,Some(Company(Google,https://www.google.com/))), Account(Henry,Twitter,Some(Company(Twitter,https://twitter.com/))), Account(Iris,Microsoft,None), Account(John,Google,Some(Company(Google,https://www.google.com/))))
```

More examples here:

https://github.com/scalikejdbc/csvquery/blob/master/src/test/scala/example/UsageSpec.scala
