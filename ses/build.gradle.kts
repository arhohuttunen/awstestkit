plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.10.1")
    implementation("software.amazon.awssdk:aws-core:2.21.42")
    implementation("software.amazon.awssdk:ses:2.21.42")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.19.3")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
}
