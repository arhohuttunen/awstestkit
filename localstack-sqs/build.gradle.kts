plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    api(project(":sqs"))
    api(project(":localstack"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.7.1")
    implementation("software.amazon.awssdk:aws-core:2.15.71")
    implementation("software.amazon.awssdk:sqs:2.15.71")
    implementation("com.amazonaws:aws-java-sdk-core:1.11.944")
    implementation("org.testcontainers:localstack:1.15.1")
    testImplementation("org.assertj:assertj-core:3.19.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
