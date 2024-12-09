plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.11.3")
    implementation("software.amazon.awssdk:aws-core:2.29.29")
    implementation("software.amazon.awssdk:sqs:2.29.29")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.20.4")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
    testImplementation("ch.qos.logback:logback-classic:1.5.12")
}
