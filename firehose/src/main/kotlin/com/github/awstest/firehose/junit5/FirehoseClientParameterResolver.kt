package com.github.awstest.firehose.junit5

import com.github.awstest.AwsClientFactory
import com.github.awstest.SdkClientParameterResolver
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.firehose.FirehoseAsyncClient
import software.amazon.awssdk.services.firehose.FirehoseAsyncClientBuilder
import software.amazon.awssdk.services.firehose.FirehoseClient
import software.amazon.awssdk.services.firehose.FirehoseClientBuilder
import kotlin.reflect.KClass

class FirehoseClientParameterResolver : SdkClientParameterResolver() {
    override val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>>

    init {
        factories = mapOf(
            FirehoseClient::class to AwsClientFactory<FirehoseClientBuilder, FirehoseClient>(FirehoseClient.builder()),
            FirehoseAsyncClient::class to AwsClientFactory<FirehoseAsyncClientBuilder, FirehoseAsyncClient>(
                FirehoseAsyncClient.builder()
            )
        )
    }
}
