package com.github.awstestkit.dynamodb.junit5

import com.github.awstestkit.AwsClientFactory
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClientBuilder
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsAsyncClient
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsAsyncClientBuilder
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsClient
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsClientBuilder

typealias DynamoDbClientFactory =
        AwsClientFactory<DynamoDbClientBuilder, DynamoDbClient>
typealias DynamoDbAsyncClientFactory =
        AwsClientFactory<DynamoDbAsyncClientBuilder, DynamoDbAsyncClient>
typealias DynamoDbStreamsClientFactory =
        AwsClientFactory<DynamoDbStreamsClientBuilder, DynamoDbStreamsClient>
typealias DynamoDbStreamsAsyncClientFactory =
        AwsClientFactory<DynamoDbStreamsAsyncClientBuilder, DynamoDbStreamsAsyncClient>
