package com.github.awstest.sqs.junit5

import com.github.awstest.AwsClientFactory
import com.github.awstest.sqs.SimpleSqsClient
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.util.AnnotationUtils
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.SqsClientBuilder
import java.lang.reflect.AnnotatedElement
import java.util.Optional

class SqsSetupExtension : BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {
    private lateinit var sqsClient: SimpleSqsClient

    override fun beforeAll(context: ExtensionContext) {
        val factory = AwsClientFactory<SqsClientBuilder, SqsClient>(SqsClient.builder())
        sqsClient = SimpleSqsClient(factory.create(context) as SqsClient)

        createResources(context.requiredTestClass)
    }

    override fun afterAll(context: ExtensionContext) {
        deleteResources(context.requiredTestClass)
    }

    override fun beforeEach(context: ExtensionContext) {
        createResources(context.requiredTestMethod)
    }

    override fun afterEach(context: ExtensionContext) {
        deleteResources(context.requiredTestMethod)
    }

    private fun createResources(annotatedElement: AnnotatedElement) {
        val annotation = findAnnotation(annotatedElement)
        if (annotation.isPresent) {
            annotation.get().queueNames.forEach {
                sqsClient.createQueue(it)
            }
        }
    }

    private fun deleteResources(annotatedElement: AnnotatedElement) {
        val annotation = findAnnotation(annotatedElement)
        if (annotation.isPresent) {
            annotation.get().queueNames.forEach {
                sqsClient.deleteQueue(it)
            }
        }
    }

    private fun findAnnotation(annotatedElement: AnnotatedElement): Optional<SqsSetup> {
        return AnnotationUtils.findAnnotation(annotatedElement, SqsSetup::class.java)
    }
}