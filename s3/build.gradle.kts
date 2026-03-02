plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:6.0.3")
    implementation("software.amazon.awssdk:aws-core:2.42.4")
    implementation("software.amazon.awssdk:s3:2.42.4")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.21.4")
    testImplementation("io.kotest:kotest-assertions-core:6.1.4")
    testImplementation("ch.qos.logback:logback-classic:1.5.32")
}
