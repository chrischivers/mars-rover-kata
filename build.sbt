ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "mars-rover",
    scalacOptions := Seq(
      "-encoding",
      "UTF-8",
      "-deprecation",
      "-feature",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-Xfatal-warnings",
      "-Ywarn-value-discard",
      "-Ywarn-unused:imports",
      "-Ymacro-annotations"
    ),
    libraryDependencies ++= Seq(
      "com.beachape" %% "enumeratum" % "1.7.2",
      "dev.optics" %% "monocle-core" % "3.1.0",
      "dev.optics" %% "monocle-macro" % "3.1.0",
      "org.typelevel" %% "cats-core" % "2.9.0",
      "org.scalameta" %% "munit" % "0.7.29" % Test
    )
  )
