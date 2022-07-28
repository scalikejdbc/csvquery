addSbtPlugin("org.xerial.sbt"   % "sbt-sonatype"         % "3.9.13")
addSbtPlugin("com.github.sbt"   % "sbt-pgp"              % "2.1.2")
addSbtPlugin("com.timushev.sbt" % "sbt-updates"          % "0.6.3")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.2")
addSbtPlugin("org.scoverage"    % "sbt-scoverage"        % "2.0.1")
addSbtPlugin("org.scoverage"    % "sbt-coveralls"        % "1.3.2")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
