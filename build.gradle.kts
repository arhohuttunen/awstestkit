import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.30" apply false
}

group = "com.github.arhohuttunen"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}
