import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  kotlin("jvm")
  id("org.lsposed.lsplugin.jgit") version "1.1"
  `maven-publish`
}

publishing {
  publications {
    create<MavenPublication>("maven") {
      from(components["java"])

      groupId = "com.github.EB-wilson.UniverseKit"
      artifactId = project.name
      version = "${rootProject.version}"
    }
  }
}

repositories {
  mavenLocal()
  mavenCentral()
  google()
}

dependencies {
  compileOnly(project(":platform:stub"))
  compileOnly("androidx.annotation:annotation:1.9.1")

  implementation(kotlin("stdlib-jdk8"))
  implementation(kotlin("reflect"))

  implementation(project(":platform:expects"))
}

java {
  withSourcesJar()
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
  jvmToolchain(21)
  
  compilerOptions {
    jvmTarget.set(JvmTarget.JVM_1_8)
  }
}