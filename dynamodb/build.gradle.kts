plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    implementation("software.amazon.awssdk:aws-core:2.16.2")
    implementation("software.amazon.awssdk:dynamodb:2.16.2")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.16.2")
    testImplementation("io.kotest:kotest-assertions-core:5.0.0")
}
