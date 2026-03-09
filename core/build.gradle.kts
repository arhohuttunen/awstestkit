plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.junit.jupiter:junit-jupiter:6.0.3")
    implementation("software.amazon.awssdk:aws-core:2.42.8")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("io.kotest:kotest-assertions-core:6.1.5")
    testImplementation("org.junit.platform:junit-platform-testkit:6.0.3")
    testImplementation("ch.qos.logback:logback-classic:1.5.32")
}
