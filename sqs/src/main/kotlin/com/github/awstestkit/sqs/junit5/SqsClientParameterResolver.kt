package com.github.awstestkit.sqs.junit5

import com.github.awstestkit.AwsClientFactory
import com.github.awstestkit.SdkClientParameterResolver
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.SqsClient
import kotlin.reflect.KClass

class SqsClientParameterResolver : SdkClientParameterResolver() {
    override val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>>

    init {
        factories = mapOf(
            SqsClient::class to SqsClientFactory(SqsClient.builder()),
            SqsAsyncClient::class to SqsAsyncClientFactory(SqsAsyncClient.builder())
        )
    }
}
