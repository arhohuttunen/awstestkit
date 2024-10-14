plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.junit.jupiter:junit-jupiter:5.11.2")
    implementation("software.amazon.awssdk:aws-core:2.28.21")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
    testImplementation("org.junit.platform:junit-platform-testkit:1.11.2")
}
