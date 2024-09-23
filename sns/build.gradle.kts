plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.11.0")
    implementation("software.amazon.awssdk:aws-core:2.28.6")
    implementation("software.amazon.awssdk:sns:2.28.6")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.20.1")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
}
