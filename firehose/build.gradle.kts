plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.13.4")
    implementation("software.amazon.awssdk:aws-core:2.33.9")
    implementation("software.amazon.awssdk:firehose:2.33.9")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.21.3")
    testImplementation("io.kotest:kotest-assertions-core:6.0.3")
    testImplementation("ch.qos.logback:logback-classic:1.5.18")
}
