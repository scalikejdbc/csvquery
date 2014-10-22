# CSVQuery 

Working with CSV files by using SQL!

## Setup

```sh
echo 'scalaVersion := "2.11.2"

resolvers += "sonatype releases" at "https://oss.sonatype.org/content/repositories/releases"

libraryDependencies += "com.github.seratch" %% "csvquery" % "0.1"' > build.sbt

echo "Alice,23
Bob,34
Chris,30" > sample.csv
```

And then try the folling things on `sbt console`.

```scala
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

// NOTE: compilation on the REPL fails, use initialCommands instead.
case class User(name: String, age: Int)
object UserDAO extends SkinnyCSVMapper[User] {
  def csv = CSV("./sample.csv", Seq("name", "age"))
  override def extract(rs: WrappedResultSet, rn: ResultName[User]) = autoConstruct(rs, rn)
}
val users = UserDAO.findAll()
val alice = UserDAO.where('name -> "Alice").apply().headOption
```

Output example:

```
scala> import scalikejdbc._
import scalikejdbc._

scala> import csvquery._
import csvquery._

scala> implicit val session = autoCSVSession
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

scala> val users = UserDAO.findAll()
13:14:29.032 [run-main-0] DEBUG s.StatementExecutor$$anon$1 - SQL execution completed

  [SQL Execution]
   select csv.name as n_on_csv, csv.age as a_on_csv from csvread('./sample.csv', 'NAME,AGE', 'UTF-8') csv; (1 ms)

  [Stack Trace]
    ...
    skinny.orm.feature.NoIdFinderFeature$class.findAll(NoIdFinderFeature.scala:48)
    $line1.$read$$iw$$iw$UserDAO$.findAll(<console>:24)
    $line10.$read$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$.<init>(<console>:24)
    $line10.$read$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$.<clinit>(<console>)
    $line10.$eval$.$print$lzycompute(<console>:7)
    $line10.$eval$.$print(<console>:6)
    $line10.$eval.$print(<console>)
    sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    java.lang.reflect.Method.invoke(Method.java:483)
    scala.tools.nsc.interpreter.IMain$ReadEvalPrint.call(IMain.scala:739)
    scala.tools.nsc.interpreter.IMain$Request.loadAndRun(IMain.scala:986)
    scala.tools.nsc.interpreter.IMain$WrappedRequest$$anonfun$loadAndRunReq$1.apply(IMain.scala:593)
    scala.tools.nsc.interpreter.IMain$WrappedRequest$$anonfun$loadAndRunReq$1.apply(IMain.scala:592)
    ...

users: List[User] = List(User(Alice,23), User(Bob,34), User(Chris,30))

scala> val alice = UserDAO.where('name -> "Alice").apply().headOption
13:14:35.929 [run-main-0] DEBUG s.StatementExecutor$$anon$1 - SQL execution completed

  [SQL Execution]
   select csv.name as n_on_csv, csv.age as a_on_csv from csvread('./sample.csv', 'NAME,AGE', 'UTF-8') csv where csv.name = 'Alice'; (1 ms)

  [Stack Trace]
    ...
    skinny.orm.feature.NoIdQueryingFeature$EntitiesSelectOperationBuilder.apply(NoIdQueryingFeature.scala:239)
    $line11.$read$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$.<init>(<console>:24)
    $line11.$read$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$$iw$.<clinit>(<console>:24)
    $line11.$eval$.$print$lzycompute(<console>:7)
    $line11.$eval$.$print(<console>:6)
    $line11.$eval.$print(<console>)
    sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    java.lang.reflect.Method.invoke(Method.java:483)
    scala.tools.nsc.interpreter.IMain$ReadEvalPrint.call(IMain.scala:739)
    scala.tools.nsc.interpreter.IMain$Request.loadAndRun(IMain.scala:986)
    scala.tools.nsc.interpreter.IMain$WrappedRequest$$anonfun$loadAndRunReq$1.apply(IMain.scala:593)
    scala.tools.nsc.interpreter.IMain$WrappedRequest$$anonfun$loadAndRunReq$1.apply(IMain.scala:592)
    scala.reflect.internal.util.ScalaClassLoader$class.asContext(ScalaClassLoader.scala:31)
    ...

alice: Option[User] = Some(User(Alice,23))

```
