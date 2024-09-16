plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.11.0")
    implementation("software.amazon.awssdk:aws-core:2.28.1")
    implementation("software.amazon.awssdk:kinesis:2.28.1")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.20.1")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
}
