addSbtPlugin("org.scalariform"  % "sbt-scalariform"      % "1.6.0")
addSbtPlugin("org.xerial.sbt"   % "sbt-sonatype"         % "1.1")
addSbtPlugin("com.jsuereth"     % "sbt-pgp"              % "1.1.0")
addSbtPlugin("com.timushev.sbt" % "sbt-updates"          % "0.3.3")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.0")
addSbtPlugin("org.scoverage"    % "sbt-scoverage"        % "1.5.0")
addSbtPlugin("org.scoverage"    % "sbt-coveralls"        % "1.0.0")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
