plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.junit.jupiter:junit-jupiter:6.0.2")
    implementation("software.amazon.awssdk:aws-core:2.41.24")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("io.kotest:kotest-assertions-core:6.1.3")
    testImplementation("org.junit.platform:junit-platform-testkit:6.0.2")
    testImplementation("ch.qos.logback:logback-classic:1.5.28")
}
