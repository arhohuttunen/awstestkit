plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.12.0")
    implementation("software.amazon.awssdk:aws-core:2.30.36")
    implementation("software.amazon.awssdk:firehose:2.30.36")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.20.6")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
    testImplementation("ch.qos.logback:logback-classic:1.5.17")
}
