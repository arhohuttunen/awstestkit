plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.8.0")
    implementation("software.amazon.awssdk:aws-core:2.16.2")
    implementation("software.amazon.awssdk:firehose:2.16.2")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.16.0")
    testImplementation("io.kotest:kotest-assertions-core:4.6.3")
}
