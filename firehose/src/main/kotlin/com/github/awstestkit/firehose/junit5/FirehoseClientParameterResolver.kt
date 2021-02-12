package com.github.awstestkit.firehose.junit5

import com.github.awstestkit.AwsClientFactory
import com.github.awstestkit.SdkClientParameterResolver
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.firehose.FirehoseAsyncClient
import software.amazon.awssdk.services.firehose.FirehoseClient
import kotlin.reflect.KClass

class FirehoseClientParameterResolver : SdkClientParameterResolver() {
    override val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>>

    init {
        factories = mapOf(
            FirehoseClient::class to FirehoseClientFactory(FirehoseClient.builder()),
            FirehoseAsyncClient::class to FirehoseAsyncClientFactory(FirehoseAsyncClient.builder())
        )
    }
}
