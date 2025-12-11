addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.6")
addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.3.1")
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.6.4")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "2.4.3")
addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.3.15")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
