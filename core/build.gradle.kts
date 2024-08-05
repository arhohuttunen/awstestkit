plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.junit.jupiter:junit-jupiter:5.10.3")
    implementation("software.amazon.awssdk:aws-core:2.26.29")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
    testImplementation("org.junit.platform:junit-platform-testkit:1.10.3")
}
