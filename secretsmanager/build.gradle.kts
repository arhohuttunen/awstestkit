plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.10.2")
    implementation("software.amazon.awssdk:aws-core:2.25.1")
    implementation("software.amazon.awssdk:secretsmanager:2.25.1")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.19.6")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
}
