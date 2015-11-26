addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform"      % "1.3.0")
addSbtPlugin("org.xerial.sbt"   % "sbt-sonatype"         % "1.0")
addSbtPlugin("com.jsuereth"     % "sbt-pgp"              % "1.0.0")
addSbtPlugin("com.timushev.sbt" % "sbt-updates"          % "0.1.7")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.4")
addSbtPlugin("org.scoverage"    % "sbt-scoverage"        % "1.3.3")
addSbtPlugin("org.scoverage"    % "sbt-coveralls"        % "1.0.0")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

