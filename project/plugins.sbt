addSbtPlugin("org.scalariform"  % "sbt-scalariform"      % "1.8.3")
addSbtPlugin("org.xerial.sbt"   % "sbt-sonatype"         % "3.9.10")
addSbtPlugin("com.github.sbt"   % "sbt-pgp"              % "2.1.2")
addSbtPlugin("com.timushev.sbt" % "sbt-updates"          % "0.6.0")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.2")
addSbtPlugin("org.scoverage"    % "sbt-scoverage"        % "1.8.2")
addSbtPlugin("org.scoverage"    % "sbt-coveralls"        % "1.3.1")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
