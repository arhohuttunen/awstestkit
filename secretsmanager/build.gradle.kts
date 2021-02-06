plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.7.1")
    implementation("software.amazon.awssdk:aws-core:2.15.71")
    implementation("software.amazon.awssdk:secretsmanager:2.15.71")
    testImplementation("org.assertj:assertj-core:3.19.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
