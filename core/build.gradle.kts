plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.junit.jupiter:junit-jupiter:5.10.0")
    implementation("software.amazon.awssdk:aws-core:2.20.162")
    testImplementation("io.kotest:kotest-assertions-core:5.7.2")
    testImplementation("org.junit.platform:junit-platform-testkit:1.10.0")
}
