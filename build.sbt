
name := "scaladays-triemap"

version := "0.1"

scalaVersion := "2.10.0"

scalacOptions ++= Seq(
  "-optimise"
)

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += "org.scala-lang" % "scala-swing" % "2.10.0"
