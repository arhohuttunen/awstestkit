plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.10.3")
    implementation("software.amazon.awssdk:aws-core:2.26.16")
    implementation("software.amazon.awssdk:cloudformation:2.26.16")
    testImplementation(project(":localstack"))
    testImplementation("org.testcontainers:localstack:1.19.8")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
}
