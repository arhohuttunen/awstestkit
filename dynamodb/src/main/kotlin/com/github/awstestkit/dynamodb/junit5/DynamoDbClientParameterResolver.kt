package com.github.awstestkit.dynamodb.junit5

import com.github.awstestkit.AwsClientFactory
import com.github.awstestkit.SdkClientParameterResolver
import org.junit.jupiter.api.extension.ExtensionContext
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsAsyncClient
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsClient
import kotlin.reflect.KClass

class DynamoDbClientParameterResolver : SdkClientParameterResolver() {
    private val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>> = mapOf(
        DynamoDbClient::class to DynamoDbClientFactory(DynamoDbClient.builder()),
        DynamoDbAsyncClient::class to DynamoDbAsyncClientFactory(DynamoDbAsyncClient.builder()),
        DynamoDbStreamsClient::class to DynamoDbStreamsClientFactory(DynamoDbStreamsClient.builder()),
        DynamoDbStreamsAsyncClient::class to DynamoDbStreamsAsyncClientFactory(DynamoDbStreamsAsyncClient.builder())
    )

    override fun isSupported(type: KClass<out Any>): Boolean {
        return factories.containsKey(type)
    }

    override fun create(type: KClass<out Any>, extensionContext: ExtensionContext): SdkClient {
        return factories[type]!!.create(extensionContext)
    }
}
