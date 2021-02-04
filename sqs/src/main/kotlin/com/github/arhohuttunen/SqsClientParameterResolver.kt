package com.github.arhohuttunen

import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.SqsAsyncClientBuilder
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.SqsClientBuilder
import kotlin.reflect.KClass

class SqsClientParameterResolver : SdkClientParameterResolver() {
    override val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, *>>

    init {
        factories = mapOf(
            SqsClient::class to AwsClientFactory<SqsClientBuilder, SqsClient>(SqsClient.builder()),
            SqsAsyncClient::class to AwsClientFactory<SqsAsyncClientBuilder, SqsAsyncClient>(SqsAsyncClient.builder())
        )
    }
}