package com.github.awstest.junit5

import com.github.awstest.AwsClientFactory
import com.github.awstest.SimpleSnsClient
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.util.AnnotationUtils
import software.amazon.awssdk.services.sns.SnsClient
import software.amazon.awssdk.services.sns.SnsClientBuilder
import java.lang.reflect.AnnotatedElement
import java.util.Optional

class SnsSetupExtension : BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {
    private lateinit var snsClient: SimpleSnsClient

    override fun beforeAll(context: ExtensionContext) {
        val factory = AwsClientFactory<SnsClientBuilder, SnsClient>(SnsClient.builder())
        snsClient = SimpleSnsClient(factory.create(context) as SnsClient)

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
            annotation.get().topicNames.forEach {
                snsClient.createTopic(it)
            }
        }
    }

    private fun deleteResources(annotatedElement: AnnotatedElement) {
        val annotation = findAnnotation(annotatedElement)
        if (annotation.isPresent) {
            annotation.get().topicNames.forEach {
                snsClient.deleteTopic(it)
            }
        }
    }

    private fun findAnnotation(annotatedElement: AnnotatedElement): Optional<SnsSetup> {
        return AnnotationUtils.findAnnotation(annotatedElement, SnsSetup::class.java)
    }
}