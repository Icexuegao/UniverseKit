import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  kotlin("jvm")
  `maven-publish`
}

val mindustryVersion = "v154"
val arcVersion = "v154"
val commonmarkVersion = "0.27.1"

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

  maven("https://maven.xpdustry.com/mindustry")
  maven("https://raw.githubusercontent.com/Zelaux/MindustryRepo/master/repository")
  maven("https://www.jitpack.io")
}

dependencies {
  compileOnly("com.github.Anuken.Arc:arc-core:$arcVersion")
  compileOnly("com.github.Anuken.Mindustry:core:$mindustryVersion")

  implementation("org.commonmark:commonmark:$commonmarkVersion")
  implementation("org.commonmark:commonmark-ext-gfm-tables:$commonmarkVersion")
  implementation("org.commonmark:commonmark-ext-gfm-strikethrough:$commonmarkVersion")
  implementation("org.commonmark:commonmark-ext-ins:$commonmarkVersion")
  implementation("org.commonmark:commonmark-ext-image-attributes:$commonmarkVersion")

  implementation(kotlin("stdlib-jdk8"))
  implementation(kotlin("reflect"))
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