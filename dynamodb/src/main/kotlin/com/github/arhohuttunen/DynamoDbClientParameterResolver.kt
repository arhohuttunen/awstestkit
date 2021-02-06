package com.github.arhohuttunen

import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClientBuilder
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder
import kotlin.reflect.KClass

class DynamoDbClientParameterResolver : SdkClientParameterResolver() {
    override val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, *>>

    init {
        factories = mapOf(
            DynamoDbClient::class to AwsClientFactory<DynamoDbClientBuilder, DynamoDbClient>(DynamoDbClient.builder()),
            DynamoDbAsyncClient::class to AwsClientFactory<DynamoDbAsyncClientBuilder, DynamoDbAsyncClient>(DynamoDbAsyncClient.builder())
        )
    }
}