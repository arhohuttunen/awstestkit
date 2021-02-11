package com.github.awstestkit.sns.junit5

import com.github.awstestkit.AwsClientFactory
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import software.amazon.awssdk.services.sns.SnsClient
import software.amazon.awssdk.services.sns.SnsClientBuilder

class TopicArnParameterResolver : ParameterResolver {
    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        return parameterContext.isAnnotated(TopicArn::class.java)
    }

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val factory = AwsClientFactory<SnsClientBuilder, SnsClient>(SnsClient.builder())
        val snsClient = SimpleSnsClient(factory.create(extensionContext))

        val annotation = parameterContext.findAnnotation(TopicArn::class.java).get()

        return snsClient.createTopic(annotation.value).topicArn()
    }
}
