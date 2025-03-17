plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.junit.jupiter:junit-jupiter:5.12.1")
    implementation("software.amazon.awssdk:aws-core:2.31.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
    testImplementation("org.junit.platform:junit-platform-testkit:1.12.1")
    testImplementation("ch.qos.logback:logback-classic:1.5.17")
}
