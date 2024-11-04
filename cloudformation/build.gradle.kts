plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.11.3")
    implementation("software.amazon.awssdk:aws-core:2.29.6")
    implementation("software.amazon.awssdk:cloudformation:2.29.6")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.20.3")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
}
