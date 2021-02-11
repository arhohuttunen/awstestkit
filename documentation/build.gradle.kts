import org.asciidoctor.gradle.jvm.AbstractAsciidoctorTask

plugins {
    kotlin("jvm")
    java
    id("org.asciidoctor.jvm.convert") version "3.3.1"
    id("org.ajoberstar.git-publish") version "3.0.0"
    id("com.avast.gradle.docker-compose") version "0.14.0"
    id("com.nike.pdm.localstack") version "0.1.0"
}

dependencies {
    testImplementation(project(":core"))
    testImplementation(project(":localstack"))
    testImplementation(project(":cloudformation"))
    testImplementation(project(":dynamodb"))
    testImplementation(project(":s3"))
    testImplementation(project(":secretsmanager"))
    testImplementation(project(":sns"))
    testImplementation(project(":sqs"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testImplementation("org.testcontainers:localstack:1.15.1")
    testImplementation("software.amazon.awssdk:cloudformation:2.15.71")
    testImplementation("software.amazon.awssdk:dynamodb:2.15.71")
    testImplementation("software.amazon.awssdk:s3:2.15.71")
    testImplementation("software.amazon.awssdk:secretsmanager:2.15.71")
    testImplementation("software.amazon.awssdk:sns:2.15.71")
    testImplementation("software.amazon.awssdk:sqs:2.15.71")
}

val snapshot = rootProject.version.toString().contains("SNAPSHOT")
val docsVersion = if (snapshot) "snapshot" else rootProject.version
val docsDir = file("$buildDir/gh-pages-docs")

gitPublish {
    repoUri.set("https://github.com/arhohuttunen/awstestkit.git")
    branch.set("gh-pages")
    sign.set(false)

    contents {
        from(docsDir) {
            into("docs")
        }
    }

    preserve {
        include("**/*")
        exclude("docs/$docsVersion/**")
    }
}

dockerCompose {
    useComposeFiles = listOf("localstack/docker-compose.yml")
}

tasks {
    withType<AbstractAsciidoctorTask>().configureEach {
        sourceSets["test"].apply {
            attributes(
                mapOf(
                    "testDir" to java.srcDirs.first()
                )
            )
            inputs.dir(java.srcDirs.first())
        }
    }

    withType<Test> {
        environment(
            mapOf(
                "AWS_URL" to "http://localhost:4566",
                "AWS_REGION" to "us-east-1",
                "AWS_ACCESS_KEY" to "dummy",
                "AWS_SECRET_KEY" to "dummy"
            )
        )
        dependsOn("startLocalStack")
        finalizedBy("killLocalStack")
    }

    asciidoctor {
        sources {
            include("**/index.adoc")
        }

        attributes(
            mapOf(
                "source-highlighter" to "rouge",
                "toc" to "left"
            )
        )
    }

    val prepareDocsForUploadToGhPages by registering(Copy::class) {
        dependsOn(asciidoctor)
        outputs.dir(docsDir)

        from("$buildDir/checksum") {
            include("published-checksum.txt")
        }
        from(asciidoctor.map { it.outputDir }) {
            include("**")
        }
        into("$docsDir/$docsVersion")
        includeEmptyDirs = false
    }

    gitPublishCopy {
        dependsOn(prepareDocsForUploadToGhPages)
    }
}
