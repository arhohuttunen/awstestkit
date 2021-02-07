plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.junit.jupiter:junit-jupiter:5.7.1")
    implementation("software.amazon.awssdk:aws-core:2.15.71")
    testImplementation("org.assertj:assertj-core:3.19.0")
    testImplementation("org.junit.platform:junit-platform-testkit:1.7.1")
}
