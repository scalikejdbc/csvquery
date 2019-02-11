addSbtPlugin("org.scalariform"  % "sbt-scalariform"      % "1.8.2")
addSbtPlugin("org.xerial.sbt"   % "sbt-sonatype"         % "2.3")
addSbtPlugin("com.jsuereth"     % "sbt-pgp"              % "1.1.2")
addSbtPlugin("com.timushev.sbt" % "sbt-updates"          % "0.4.0")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.0")
addSbtPlugin("org.scoverage"    % "sbt-scoverage"        % "1.5.1")
addSbtPlugin("org.scoverage"    % "sbt-coveralls"        % "1.2.5")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
