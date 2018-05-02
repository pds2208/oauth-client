import Dependencies._
import sbt.Keys.libraryDependencies

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "gov.ons",
      scalaVersion := "2.12.5",
      version := "0.1.0-SNAPSHOT"
    )),

    name := "Hello",
    libraryDependencies ++= Seq(
      "org.springframework.boot" % "spring-boot-starter-web" % "1.5.3.RELEASE",
      "org.springframework.boot" % "spring-boot-configuration-processor" % "1.5.3.RELEASE",
      "org.springframework.boot" % "spring-boot-starter-actuator" % "1.5.3.RELEASE",
      "org.springframework.boot" % "spring-boot-starter-security" % "1.5.3.RELEASE",
      "org.springframework.security.oauth" % "spring-security-oauth2" % "2.3.2.RELEASE",
      "org.keycloak" % "keycloak-spring-boot-starter" % "3.1.0.Final",
      "org.keycloak.bom" % "keycloak-adapter-bom" % "3.1.0.Final" pomOnly(),
      scalaTest % Test
    )
  )
