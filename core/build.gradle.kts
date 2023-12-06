plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.junit.jupiter:junit-jupiter:5.10.1")
    implementation("software.amazon.awssdk:aws-core:2.21.39")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("org.junit.platform:junit-platform-testkit:1.10.1")
}
