package com.github.arhohuttunen

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.util.AnnotationUtils
import software.amazon.awssdk.services.sns.SnsClient
import software.amazon.awssdk.services.sns.model.CreateTopicRequest
import software.amazon.awssdk.services.sns.model.CreateTopicResponse
import software.amazon.awssdk.services.sns.model.DeleteTopicRequest
import java.lang.reflect.AnnotatedElement
import java.util.Optional

class LocalStackSnsExtension : BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {
    private lateinit var snsClient: SnsClient

    override fun beforeAll(context: ExtensionContext) {
        snsClient = SnsClientParameterResolver().createClient(SnsClient::class, context) as SnsClient

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
                createTopic(it)
            }
        }
    }

    private fun deleteResources(annotatedElement: AnnotatedElement) {
        val annotation = findAnnotation(annotatedElement)
        if (annotation.isPresent) {
            annotation.get().topicNames.forEach {
                deleteTopic(it)
            }
        }
    }

    private fun createTopic(topicName: String): CreateTopicResponse {
        val request = CreateTopicRequest.builder()
            .name(topicName)
            .build()
        return snsClient.createTopic(request)
    }

    private fun deleteTopic(topicName: String) {
        val request = DeleteTopicRequest.builder()
            .topicArn(createTopic(topicName).topicArn())
            .build()
        snsClient.deleteTopic(request)
    }

    private fun findAnnotation(annotatedElement: AnnotatedElement): Optional<LocalStackSns> {
        return AnnotationUtils.findAnnotation(annotatedElement, LocalStackSns::class.java)
    }
}