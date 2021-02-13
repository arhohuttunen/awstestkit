import io.gitlab.arturbosch.detekt.DetektPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.30" apply false
    java
    jacoco
    id("io.gitlab.arturbosch.detekt") version "1.15.0"
}

allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }

    apply<JacocoPlugin>()

    configure<JacocoPluginExtension> {
        toolVersion = "0.8.6"
    }
}

subprojects {
    apply<DetektPlugin>()

    tasks {
        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

tasks.register<JacocoReport>("codeCoverageReport") {
    subprojects.filter { it != project(":documentation") }.forEach { subproject ->
        subproject.plugins.withType<JacocoPlugin>().configureEach {
            subproject.tasks.matching { it.extensions.findByType<JacocoTaskExtension>() != null }.configureEach {
                val testTask = this
                sourceSets(subproject.sourceSets.main.get())
                executionData(testTask)
            }
        }
    }

    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }
}
