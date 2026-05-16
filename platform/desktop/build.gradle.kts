import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  kotlin("jvm")
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
}

dependencies {
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