package com.github.awstestkit.sqs.junit5

import com.github.awstestkit.AwsClientFactory
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.SqsClientBuilder

class QueueUrlParameterResolver : ParameterResolver {
    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        return parameterContext.isAnnotated(QueueUrl::class.java)
    }

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val factory = AwsClientFactory<SqsClientBuilder, SqsClient>(SqsClient.builder())
        val sqsClient = SimpleSqsClient(factory.create(extensionContext))

        val annotation = parameterContext.findAnnotation(QueueUrl::class.java).get()

        return sqsClient.getQueueUrl(annotation.value)
    }
}
