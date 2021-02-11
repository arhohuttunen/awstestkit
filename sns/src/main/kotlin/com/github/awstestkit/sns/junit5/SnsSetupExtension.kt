package com.github.awstestkit.sns.junit5

import com.github.awstestkit.AwsClientFactory
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.util.AnnotationUtils
import software.amazon.awssdk.services.sns.SnsClient
import software.amazon.awssdk.services.sns.SnsClientBuilder
import java.lang.reflect.AnnotatedElement

class SnsSetupExtension : BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {
    private lateinit var snsClient: SimpleSnsClient

    override fun beforeAll(context: ExtensionContext) {
        val factory = AwsClientFactory<SnsClientBuilder, SnsClient>(SnsClient.builder())
        snsClient = SimpleSnsClient(factory.create(context))

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
        val topics = findTopics(annotatedElement)
        topics.forEach {
            snsClient.createTopic(it.value)
        }
    }

    private fun deleteResources(annotatedElement: AnnotatedElement) {
        val topics = findTopics(annotatedElement)
        topics.forEach {
            snsClient.deleteTopic(it.value)
        }
    }

    private fun findTopics(annotatedElement: AnnotatedElement): List<SnsTopic> {
        val topic = findSnsTopic(annotatedElement)
        topic?.apply {
            return listOf(topic)
        }
        return findSnsTopics(annotatedElement)
    }

    private fun findSnsTopic(annotatedElement: AnnotatedElement): SnsTopic? {
        return AnnotationUtils.findAnnotation(annotatedElement, SnsTopic::class.java).orElse(null)
    }

    private fun findSnsTopics(annotatedElement: AnnotatedElement): List<SnsTopic> {
        val topics = AnnotationUtils.findAnnotation(annotatedElement, SnsTopics::class.java)
        if (topics.isPresent) {
            return topics.get().value.toList()
        }
        return emptyList()
    }
}
