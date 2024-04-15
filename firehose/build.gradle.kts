plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.10.2")
    implementation("software.amazon.awssdk:aws-core:2.25.31")
    implementation("software.amazon.awssdk:firehose:2.25.31")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.19.7")
    testImplementation("io.kotest:kotest-assertions-core:5.8.1")
}
