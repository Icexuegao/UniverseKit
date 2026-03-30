import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  kotlin("jvm") version "2.2.20"
}

val mindustryVersion = "v154"
val arcVersion = "v154"

group = "com.github.ebwilson"
version = "1.0"

repositories {
  mavenLocal()
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation(files("runnable-hiero.jar"))
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
  jvmToolchain(21)
  
  compilerOptions {
    jvmTarget.set(JvmTarget.JVM_1_8)
  }
}