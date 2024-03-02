package com.github.awstestkit.localstack.junit5

import com.github.awstestkit.cloudformation.junit5.CloudFormationTest
import com.github.awstestkit.dynamodb.junit5.DynamoDbTest
import com.github.awstestkit.firehose.junit5.FirehoseTest
import com.github.awstestkit.kinesis.junit5.KinesisTest
import com.github.awstestkit.kms.junit5.KmsTest
import com.github.awstestkit.s3.junit5.S3Test
import com.github.awstestkit.secretsmanager.junit5.SecretsManagerTest
import com.github.awstestkit.ses.junit5.SesTest
import com.github.awstestkit.sns.junit5.SnsTest
import com.github.awstestkit.sqs.junit5.SqsTest
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.util.AnnotationUtils
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.utility.DockerImageName
import java.lang.reflect.AnnotatedElement

internal const val NAMESPACE = "com.github.arhohuttunen.awsclient-junit5"

class LocalStackExtension : BeforeAllCallback, AfterAllCallback {
    private val namespace = ExtensionContext.Namespace.create(NAMESPACE)

    private lateinit var container: LocalStackContainer

    override fun beforeAll(context: ExtensionContext) {
        val testClass = context.requiredTestClass
        val annotation = testClass.getAnnotation(LocalStackTest::class.java)

        val services = annotation.services.toMutableSet() + scanServiceAnnotations(testClass)

        @Suppress("SpreadOperator")
        container = LocalStackContainer(DockerImageName.parse("localstack/localstack:3.2"))
            .withServices(*services.toTypedArray())
        container.start()

        context.getStore(namespace).put("container", container)
    }

    override fun afterAll(context: ExtensionContext) {
        container.stop()
        context.getStore(namespace).remove("container")
    }

    private fun scanServiceAnnotations(testClass: AnnotatedElement): Set<LocalStackContainer.Service> {
        val services = mutableSetOf<LocalStackContainer.Service>()

        val annotationToService = mapOf(
            CloudFormationTest::class.java to LocalStackContainer.Service.CLOUDFORMATION,
            DynamoDbTest::class.java to LocalStackContainer.Service.DYNAMODB,
            FirehoseTest::class.java to LocalStackContainer.Service.FIREHOSE,
            KinesisTest::class.java to LocalStackContainer.Service.KINESIS,
            KmsTest::class.java to LocalStackContainer.Service.KMS,
            S3Test::class.java to LocalStackContainer.Service.S3,
            SecretsManagerTest::class.java to LocalStackContainer.Service.SECRETSMANAGER,
            SesTest::class.java to LocalStackContainer.Service.SES,
            SnsTest::class.java to LocalStackContainer.Service.SNS,
            SqsTest::class.java to LocalStackContainer.Service.SQS
        )

        annotationToService.forEach { (key, value) ->
            val annotation = AnnotationUtils.findAnnotation(testClass, key)
            annotation.ifPresent { services += value }
        }

        return services
    }
}
