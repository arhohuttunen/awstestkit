plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.13.1")
    implementation("software.amazon.awssdk:aws-core:2.31.59")
    implementation("software.amazon.awssdk:cloudformation:2.31.59")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.21.1")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
    testImplementation("ch.qos.logback:logback-classic:1.5.18")
}
