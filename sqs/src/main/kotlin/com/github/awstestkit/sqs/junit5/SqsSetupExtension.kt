package com.github.awstestkit.sqs.junit5

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.util.AnnotationUtils
import software.amazon.awssdk.services.sqs.SqsClient
import java.lang.reflect.AnnotatedElement

class SqsSetupExtension : BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {
    private lateinit var sqsClient: SimpleSqsClient

    override fun beforeAll(context: ExtensionContext) {
        val factory = SqsClientFactory(SqsClient.builder())
        sqsClient = SimpleSqsClient(factory.create(context))

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
        val queues = findQueues(annotatedElement)
        queues.forEach {
            sqsClient.createQueue(it.value)
        }
    }

    private fun deleteResources(annotatedElement: AnnotatedElement) {
        val queues = findQueues(annotatedElement)
        queues.forEach {
            sqsClient.deleteQueue(it.value)
        }
    }

    private fun findQueues(annotatedElement: AnnotatedElement): List<SqsQueue> {
        val topic = findSqsQueue(annotatedElement)
        topic?.apply {
            return listOf(topic)
        }
        return findSqsQueues(annotatedElement)
    }

    private fun findSqsQueue(annotatedElement: AnnotatedElement): SqsQueue? {
        return AnnotationUtils.findAnnotation(annotatedElement, SqsQueue::class.java).orElse(null)
    }

    private fun findSqsQueues(annotatedElement: AnnotatedElement): List<SqsQueue> {
        val topics = AnnotationUtils.findAnnotation(annotatedElement, SqsQueues::class.java)
        if (topics.isPresent) {
            return topics.get().value.toList()
        }
        return emptyList()
    }
}
