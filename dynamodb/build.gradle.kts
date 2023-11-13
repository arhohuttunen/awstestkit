plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.10.1")
    implementation("software.amazon.awssdk:aws-core:2.21.21")
    implementation("software.amazon.awssdk:dynamodb:2.21.21")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.19.1")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
}
