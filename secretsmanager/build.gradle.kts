plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.10.3")
    implementation("software.amazon.awssdk:aws-core:2.26.25")
    implementation("software.amazon.awssdk:secretsmanager:2.26.25")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.20.0")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
}
