addSbtPlugin("org.scalariform"  % "sbt-scalariform"      % "1.8.3")
addSbtPlugin("org.xerial.sbt"   % "sbt-sonatype"         % "3.9.2")
addSbtPlugin("com.jsuereth"     % "sbt-pgp"              % "1.1.2")
addSbtPlugin("com.timushev.sbt" % "sbt-updates"          % "0.5.1")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.2")
addSbtPlugin("org.scoverage"    % "sbt-scoverage"        % "1.6.1")
addSbtPlugin("org.scoverage"    % "sbt-coveralls"        % "1.2.7")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
