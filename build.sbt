ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "mars-rover"
  )

libraryDependencies ++= Seq(
  "io.monix"      %% "newtypes-core" % "0.2.3",
  "com.beachape"  %% "enumeratum"    % "1.7.2",
  "org.scalameta" %% "munit"         % "0.7.29" % Test
)
