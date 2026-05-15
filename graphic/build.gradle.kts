import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  kotlin("jvm")
  `maven-publish`
}

val mindustryVersion = "v154"
val arcVersion = "v154"

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

  maven ("https://maven.xpdustry.com/mindustry")
  maven { url = uri("https://raw.githubusercontent.com/Zelaux/MindustryRepo/master/repository") }
  maven { url = uri("https://www.jitpack.io") }
}

dependencies {
  compileOnly("com.github.Anuken.Arc:arc-core:${arcVersion}")
  compileOnly("com.github.Anuken.Mindustry:core:${mindustryVersion}")
  testImplementation("com.github.Anuken.Arc:arc-core:${arcVersion}")
  testImplementation("com.github.Anuken.Mindustry:core:${mindustryVersion}")
  implementation(kotlin("stdlib-jdk8"))
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