scalaVersion := "2.11.7"

//::enablePlugins(SbtNativePackager)


name := "skokk"

version := "1.0"

enablePlugins(JettyPlugin)

enablePlugins(JavaServerAppPackaging)

resolvers += Resolver.bintrayRepo("oncue","releases")

resolvers += sbt.Resolver.bintrayRepo("denigma", "denigma-releases")
//ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

mainClass in Compile := Some("no.skokk.Runner")

packageSummary := "Skokk Web standalone"

enablePlugins(ScalaJSPlugin)


scalaJSStage in Global := FastOptStage

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.8.0",
//  "be.doeraene" %%% "scalajs-jquery" % "0.8.0",
//  "org.denigma" %%% "threejs-facade" % "0.0.71-0.1.5",
//  "com.github.japgolly.scalajs-react" %%% "core" % "0.9.2",
  "io.argonaut" %% "argonaut" % "6.1",
  "net.databinder" %% "unfiltered-filter" % "0.8.4",
  "net.databinder" %% "unfiltered-jetty" % "0.8.4",
  "net.databinder" %% "unfiltered-filter-async" % "0.8.4",
  //  "net.databinder" %% "unfiltered-json" % "0.8.4",
  "net.databinder.dispatch" %%  "dispatch-core"     % "0.11.2",
  "org.scalaz" %% "scalaz-core" % "7.1.3",
  "com.chuusai" %% "shapeless" % "2.2.4",
  "org.apache.spark" %% "spark-core" % "1.4.1",
  "com.typesafe.slick" %% "slick-codegen" % "3.0.2",
  "com.typesafe.slick" %% "slick" % "3.0.2",
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "joda-time" % "joda-time" % "2.7",
  "org.spire-math" %% "spire" % "0.10.1"
)

scalacOptions += "-feature"

initialCommands in console := "import shapeless._ "

jsDependencies +=
  "org.webjars" % "react" % "0.12.2" / "react-with-addons.js" commonJSName "React"

