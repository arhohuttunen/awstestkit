package com.github.awstestkit.sns.junit5

import com.github.awstestkit.AwsClientFactory
import com.github.awstestkit.SdkClientFactory
import com.github.awstestkit.SdkClientParameterResolver
import org.junit.jupiter.api.extension.ExtensionContext
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.sns.SnsAsyncClient
import software.amazon.awssdk.services.sns.SnsClient
import kotlin.reflect.KClass

class SnsClientParameterResolver : SdkClientFactory, SdkClientParameterResolver() {
    private val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>>

    init {
        factories = mapOf(
            SnsClient::class to SnsClientFactory(SnsClient.builder()),
            SnsAsyncClient::class to SnsAsyncClientFactory(SnsAsyncClient.builder())
        )
    }

    override fun isSupported(type: KClass<out Any>): Boolean {
        return factories.containsKey(type)
    }

    override fun create(type: KClass<out Any>, extensionContext: ExtensionContext): SdkClient {
        return factories[type]!!.create(extensionContext)
    }
}
