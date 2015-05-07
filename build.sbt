name := """adam-test"""

version := "1.0"

scalaVersion := "2.10.4"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.9"

libraryDependencies += "org.bdgenomics.adam" % "adam-core" % "0.16.0"

com.github.retronym.SbtOneJar.oneJarSettings

mainClass in oneJar := Some("App")

assemblyJarName in assembly := "adam-test.jar"

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

assemblyMergeStrategy in assembly <<= (assemblyMergeStrategy in assembly) { (old) =>
{
  case m if m.toLowerCase.matches("meta-inf/.*\\.sf$") => MergeStrategy.discard
  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.first
  case PathList("org", "apache", xs @ _*) => MergeStrategy.first
  case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
  case "about.html" => MergeStrategy.rename
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case x =>  MergeStrategy.last
}  }

assemblyExcludedJars in assembly <<= (fullClasspath in assembly) map { cp =>
  cp filter {_.data.getName.contains(".hadoop.")}
}

fork in run := true