addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.4")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.9.21")
addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.3.1")
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.6.4")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.2")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "2.3.1")
addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.3.15")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
