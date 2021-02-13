package com.github.awstestkit.kinesis.junit5

import com.github.awstestkit.AwsClientFactory
import com.github.awstestkit.SdkClientParameterResolver
import org.junit.jupiter.api.extension.ExtensionContext
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient
import software.amazon.awssdk.services.kinesis.KinesisClient
import kotlin.reflect.KClass

class KinesisClientParameterResolver : SdkClientParameterResolver() {
    private val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>>

    init {
        factories = mapOf(
            KinesisClient::class to KinesisClientFactory(KinesisClient.builder()),
            KinesisAsyncClient::class to KinesisAsyncClientFactory(KinesisAsyncClient.builder())
        )
    }

    override fun isSupported(type: KClass<out Any>): Boolean {
        return factories.containsKey(type)
    }

    override fun create(type: KClass<out Any>, extensionContext: ExtensionContext): SdkClient {
        return factories[type]!!.create(extensionContext)
    }
}
