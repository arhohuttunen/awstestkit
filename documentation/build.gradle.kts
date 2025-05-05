import org.asciidoctor.gradle.jvm.AbstractAsciidoctorTask

plugins {
    kotlin("jvm")
    java
    id("org.asciidoctor.jvm.convert") version "4.0.4"
    id("org.ajoberstar.git-publish") version "5.1.1"
    id("com.avast.gradle.docker-compose") version "0.17.12"
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
    testImplementation("org.junit.jupiter:junit-jupiter:5.12.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.testcontainers:localstack:1.21.0")
    testImplementation("software.amazon.awssdk:cloudformation:2.31.35")
    testImplementation("software.amazon.awssdk:dynamodb:2.31.35")
    testImplementation("software.amazon.awssdk:s3:2.31.35")
    testImplementation("software.amazon.awssdk:secretsmanager:2.31.35")
    testImplementation("software.amazon.awssdk:sns:2.31.35")
    testImplementation("software.amazon.awssdk:sqs:2.31.35")
    testImplementation("ch.qos.logback:logback-classic:1.5.18")
}

val snapshot = rootProject.version.toString().contains("SNAPSHOT")
val docsVersion = if (snapshot) "snapshot" else rootProject.version
val docsDir = file("${layout.buildDirectory}/gh-pages-docs")

gitPublish {
    repoUri.set("https://github.com/arhohuttunen/awstestkit.git")
    branch.set("gh-pages")
    username.set("arhohuttunen")
    password.set(System.getenv("GITHUB_TOKEN"))
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
    useComposeFiles.set(listOf("localstack/docker-compose.yml"))
    isRequiredBy(project.tasks["test"])
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

        from("${layout.buildDirectory}/checksum") {
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
