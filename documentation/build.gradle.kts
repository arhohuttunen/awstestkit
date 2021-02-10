import org.asciidoctor.gradle.jvm.AbstractAsciidoctorTask

plugins {
    kotlin("jvm")
    java
    id("org.asciidoctor.jvm.convert") version "3.3.1"
    id("org.ajoberstar.git-publish") version "3.0.0"
}

dependencies {
    testImplementation(project(":core"))
    testImplementation(project(":cloudformation"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testImplementation("software.amazon.awssdk:cloudformation:2.15.71")
}

val snapshot = rootProject.version.toString().contains("SNAPSHOT")
val docsVersion = if (snapshot) "snapshot" else rootProject.version
val docsDir = file("$buildDir/docs/asciidoc")

gitPublish {
    repoUri.set("https://github.com/arhohuttunen/awstestkit.git")
    branch.set("gh-pages")
    sign.set(false)

    contents {
        from(docsDir)
        into("docs")
    }

    preserve {
        include("**/*")
        exclude("docs/**")
    }
}

tasks {
    withType<AbstractAsciidoctorTask>().configureEach {
        sourceSets["test"].apply {
            attributes(mapOf(
                "testDir" to java.srcDirs.first()
            ))
            inputs.dir(java.srcDirs.first())
        }
    }

    asciidoctor {
        sources {
            include("**/index.adoc")
        }
    }

    gitPublishCommit {
        dependsOn(asciidoctor)
    }
}