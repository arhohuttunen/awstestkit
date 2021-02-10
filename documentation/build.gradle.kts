import org.asciidoctor.gradle.jvm.AbstractAsciidoctorTask
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import kotlin.collections.mapOf

plugins {
    kotlin("jvm")
    id("org.asciidoctor.jvm.convert") version "3.3.1"
}

dependencies {
    testImplementation(project(":core"))
    testImplementation(project(":cloudformation"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testImplementation("software.amazon.awssdk:cloudformation:2.15.71")
}

tasks {
    withType<AbstractAsciidoctorTask>().configureEach {
        sourceSets["test"].apply {
            withConvention(KotlinSourceSet::class) {
                attributes(mapOf("kotlinTestDir" to kotlin.srcDirs.first()))
                inputs.dir(kotlin.srcDirs.first())
            }
        }
    }

    asciidoctor {
        sources {
            include("**/index.adoc")
        }
    }
}
