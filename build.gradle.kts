import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.31" apply false
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

    detekt {
        input = objects.fileCollection().from(
            DetektExtension.DEFAULT_SRC_DIR_JAVA,
            "src/test/java",
            DetektExtension.DEFAULT_SRC_DIR_KOTLIN,
            "src/test/kotlin"
        )
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
        xml.destination = file("${buildDir}/reports/jacoco/report.xml")
        html.isEnabled = false
    }
}
