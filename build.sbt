//import scalariform.formatter.preferences._
//
name := "firstApp"

version := "1.0-SNAPSHOT"

//Scala version
scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  "org.scalatestplus" % "play_2.10" % "1.3.0" % "test",	
  "mysql" % "mysql-connector-java" % "5.1.22",
  "com.typesafe.slick" %% "slick" % "2.1.0"
)

//assemblySettings

mainClass in assembly := Some("play.core.server.NettyServer")

//fullClasspath in assembly += Attributed.blank(PlayKeys.playPackageAssets.value)

//fullClasspath in assembly += Attributed.blank(PlayKeys.playPackageAssets.value)    

//jacoco.settings

play.Project.playScalaSettings

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
    case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
    case PathList("org", "apache", xs @ _*) => MergeStrategy.last
    case PathList("com", "google", xs @ _*) => MergeStrategy.last
    case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
    case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
    case PathList("com", "yammer", xs @ _*) => MergeStrategy.last
    case "about.html" => MergeStrategy.rename
    case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
    case "META-INF/mailcap" => MergeStrategy.last
    case "META-INF/mimetypes.default" => MergeStrategy.last
    case "plugin.properties" => MergeStrategy.last
	case "play/core/server/ServerWithStop.class" => MergeStrategy.first
    case "log4j.properties" => MergeStrategy.last
    case x => old(x)
  }
}

assemblyMergeStrategy in assembly := {
  case PathList("play", "core", "server", "ServerWithStop.class") => MergeStrategy.first
  case other => (assemblyMergeStrategy in assembly).value(other)
}