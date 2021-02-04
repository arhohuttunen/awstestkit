package com.github.arhohuttunen

import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.sns.SnsAsyncClient
import software.amazon.awssdk.services.sns.SnsAsyncClientBuilder
import software.amazon.awssdk.services.sns.SnsClient
import software.amazon.awssdk.services.sns.SnsClientBuilder
import kotlin.reflect.KClass

class SnsClientParameterResolver : SdkClientParameterResolver() {
    override val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, *>>

    init {
        factories = mapOf(
            SnsClient::class to AwsClientFactory<SnsClientBuilder, SnsClient>(SnsClient.builder()),
            SnsAsyncClient::class to AwsClientFactory<SnsAsyncClientBuilder, SnsAsyncClient>(SnsAsyncClient.builder())
        )
    }
}