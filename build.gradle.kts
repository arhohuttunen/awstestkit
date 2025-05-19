import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.1.21" apply false
    java
    jacoco
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
}

allprojects {
    repositories {
        mavenCentral()
    }

    apply<JacocoPlugin>()

    configure<JacocoPluginExtension> {
        toolVersion = "0.8.7"
    }
}

subprojects {
    apply<DetektPlugin>()

    tasks {
        withType<KotlinCompile> {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_1_8)
            }
        }
        withType<JavaCompile> {
            sourceCompatibility = "1.8"
            targetCompatibility = "1.8"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    detekt {
        source.setFrom(
            objects.fileCollection().from(
                DetektExtension.DEFAULT_SRC_DIR_JAVA,
                "src/test/java",
                DetektExtension.DEFAULT_SRC_DIR_KOTLIN,
                "src/test/kotlin"
            )
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
        xml.required.set(true)
        xml.outputLocation.set(layout.buildDirectory.file("reports/jacoco/report.xml"))
        html.required.set(false)
    }
}
