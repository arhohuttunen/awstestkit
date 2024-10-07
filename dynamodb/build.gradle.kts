plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.11.2")
    implementation("software.amazon.awssdk:aws-core:2.28.16")
    implementation("software.amazon.awssdk:dynamodb:2.28.16")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.20.2")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
}
