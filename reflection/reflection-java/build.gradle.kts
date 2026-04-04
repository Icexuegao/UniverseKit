plugins {
  java
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
  implementation(project(":platform"))
  implementation(project(":platform:expects"))
  implementation(project(":reflection"))

  implementation(kotlin("stdlib-jdk8"))

  testImplementation(project(":reflection"))
}

java {
  withSourcesJar()
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}