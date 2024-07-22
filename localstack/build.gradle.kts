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
    implementation("org.junit.jupiter:junit-jupiter:5.10.3")
    implementation("software.amazon.awssdk:aws-core:2.26.21")
    implementation("com.amazonaws:aws-java-sdk-core:1.12.762")
    implementation("org.testcontainers:localstack:1.20.0")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
}
