plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation(project(":cloudformation"))
    implementation(project(":dynamodb"))
    implementation(project(":firehose"))
    implementation(project(":kinesis"))
    implementation(project(":kms"))
    implementation(project(":s3"))
    implementation(project(":secretsmanager"))
    implementation(project(":ses"))
    implementation(project(":sns"))
    implementation(project(":sqs"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:6.0.1")
    implementation("software.amazon.awssdk:aws-core:2.36.2")
    implementation("com.amazonaws:aws-java-sdk-core:1.12.792")
    implementation("org.testcontainers:localstack:1.21.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
    testImplementation("ch.qos.logback:logback-classic:1.5.18")
}
