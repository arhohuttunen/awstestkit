plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.11.3")
    implementation("software.amazon.awssdk:aws-core:2.29.23")
    implementation("software.amazon.awssdk:s3:2.29.23")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.20.4")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
}
