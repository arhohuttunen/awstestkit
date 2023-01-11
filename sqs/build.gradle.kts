plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.9.2")
    implementation("software.amazon.awssdk:aws-core:2.16.2")
    implementation("software.amazon.awssdk:sqs:2.16.2")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.17.6")
    testImplementation("io.kotest:kotest-assertions-core:5.5.4")
}
