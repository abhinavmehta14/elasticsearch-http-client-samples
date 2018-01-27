name := "elasticsearch-client"

version := "1.0"

scalaVersion := "2.12.4"
val elastic4sVersion = "6.1.2"
/*
Match elastic4sVersion to Elasticsearch version running.
Most of the library code used from elastic4s package works
with this specific Elasticsearch version though.
  */

libraryDependencies ++= Seq(
    "com.sksamuel.elastic4s" %% "elastic4s-core" % elastic4sVersion,
    "com.sksamuel.elastic4s" %% "elastic4s-tcp" % elastic4sVersion,
    "com.sksamuel.elastic4s" %% "elastic4s-http" % elastic4sVersion
)

resolvers += "Releases" at "http://nexus-dev/content/repositories/releases"