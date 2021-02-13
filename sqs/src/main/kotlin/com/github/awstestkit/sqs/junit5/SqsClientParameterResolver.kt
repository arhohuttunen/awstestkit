package com.github.awstestkit.sqs.junit5

import com.github.awstestkit.AwsClientFactory
import com.github.awstestkit.SdkClientParameterResolver
import org.junit.jupiter.api.extension.ExtensionContext
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.SqsClient
import kotlin.reflect.KClass

class SqsClientParameterResolver : SdkClientParameterResolver() {
    private val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>>

    init {
        factories = mapOf(
            SqsClient::class to SqsClientFactory(SqsClient.builder()),
            SqsAsyncClient::class to SqsAsyncClientFactory(SqsAsyncClient.builder())
        )
    }

    override fun isSupported(type: KClass<out Any>): Boolean {
        return factories.containsKey(type)
    }

    override fun create(type: KClass<out Any>, extensionContext: ExtensionContext): SdkClient {
        return factories[type]!!.create(extensionContext)
    }
}
