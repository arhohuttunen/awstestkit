package com.github.awstestkit.kinesis.junit5

import com.github.awstestkit.AwsClientFactory
import com.github.awstestkit.SdkClientParameterResolver
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient
import software.amazon.awssdk.services.kinesis.KinesisAsyncClientBuilder
import software.amazon.awssdk.services.kinesis.KinesisClient
import software.amazon.awssdk.services.kinesis.KinesisClientBuilder
import kotlin.reflect.KClass

class KinesisClientParameterResolver : SdkClientParameterResolver() {
    override val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>>

    init {
        factories = mapOf(
            KinesisClient::class to AwsClientFactory<KinesisClientBuilder, KinesisClient>(KinesisClient.builder()),
            KinesisAsyncClient::class to AwsClientFactory<KinesisAsyncClientBuilder, KinesisAsyncClient>(
                KinesisAsyncClient.builder()
            )
        )
    }
}
