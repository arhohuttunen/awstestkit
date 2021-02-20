plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.7.1")
    implementation("software.amazon.awssdk:aws-core:2.16.2")
    implementation("software.amazon.awssdk:dynamodb:2.16.2")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.15.2")
    testImplementation("org.assertj:assertj-core:3.19.0")
}
