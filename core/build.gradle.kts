plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.junit.jupiter:junit-jupiter:5.9.0")
    implementation("software.amazon.awssdk:aws-core:2.16.2")
    testImplementation("io.kotest:kotest-assertions-core:5.4.0")
    testImplementation("org.junit.platform:junit-platform-testkit:1.9.0")
}
