package com.github.awstestkit.dynamodb.junit5

import com.github.awstestkit.AwsClientFactory
import com.github.awstestkit.SdkClientParameterResolver
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsAsyncClient
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsClient
import kotlin.reflect.KClass

class DynamoDbClientParameterResolver : SdkClientParameterResolver() {
    override val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>>

    init {
        factories = mapOf(
            DynamoDbClient::class to DynamoDbClientFactory(DynamoDbClient.builder()),
            DynamoDbAsyncClient::class to DynamoDbAsyncClientFactory(DynamoDbAsyncClient.builder()),
            DynamoDbStreamsClient::class to DynamoDbStreamsClientFactory(DynamoDbStreamsClient.builder()),
            DynamoDbStreamsAsyncClient::class to DynamoDbStreamsAsyncClientFactory(DynamoDbStreamsAsyncClient.builder())
        )
    }
}
