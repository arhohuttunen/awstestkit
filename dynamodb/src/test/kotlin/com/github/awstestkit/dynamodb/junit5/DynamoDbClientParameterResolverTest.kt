package com.github.awstestkit.dynamodb.junit5

import com.github.awstestkit.AwsClient
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsAsyncClient
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsClient

@ExtendWith(DynamoDbClientParameterResolver::class)
class DynamoDbClientParameterResolverTest {
    @Test
    fun `resolve DynamoDB client as an argument`(@AwsClient client: DynamoDbClient) {
        client shouldNotBe null
    }

    @Test
    fun `resolve DynamoDB async client as an argument`(@AwsClient client: DynamoDbClient) {
        client shouldNotBe null
    }

    @Test
    fun `resolve DynamoDB Streams client as an argument`(@AwsClient client: DynamoDbStreamsClient) {
        client shouldNotBe null
    }

    @Test
    fun `resolve DynamoDB Streams async client as an argument`(@AwsClient client: DynamoDbStreamsAsyncClient) {
        client shouldNotBe null
    }
}
