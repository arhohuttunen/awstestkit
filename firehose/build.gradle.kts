plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:6.0.1")
    implementation("software.amazon.awssdk:aws-core:2.36.2")
    implementation("software.amazon.awssdk:firehose:2.36.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.21.3")
    testImplementation("io.kotest:kotest-assertions-core:6.0.7")
    testImplementation("ch.qos.logback:logback-classic:1.5.21")
}
