import org.gradle.kotlin.dsl.mavenCentral

rootProject.name = "UniverseKit"

pluginManagement {
  repositories {
    mavenLocal()
    mavenCentral()

    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }

    gradlePluginPortal()
  }
}

include("markdown")
include("reflection")
include("reflection:reflection-java")
include("graphic")
include("platform")

include("platform:expects")
include("platform:desktop")
include("platform:desktop9")
include("platform:android")
include("platform:android29")
include("platform:stub")