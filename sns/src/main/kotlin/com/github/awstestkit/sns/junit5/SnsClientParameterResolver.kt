package com.github.awstestkit.sns.junit5

import com.github.awstestkit.AwsClientFactory
import com.github.awstestkit.SdkClientParameterResolver
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.sns.SnsAsyncClient
import software.amazon.awssdk.services.sns.SnsClient
import kotlin.reflect.KClass

class SnsClientParameterResolver : SdkClientParameterResolver() {
    override val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>>

    init {
        factories = mapOf(
            SnsClient::class to SnsClientFactory(SnsClient.builder()),
            SnsAsyncClient::class to SnsAsyncClientFactory(SnsAsyncClient.builder())
        )
    }
}
