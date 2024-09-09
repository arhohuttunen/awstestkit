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
    implementation("org.junit.jupiter:junit-jupiter:5.11.0")
    implementation("software.amazon.awssdk:aws-core:2.27.21")
    implementation("com.amazonaws:aws-java-sdk-core:1.12.771")
    implementation("org.testcontainers:localstack:1.20.1")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
}
