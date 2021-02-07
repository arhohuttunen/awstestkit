package com.github.awstest.dynamodb.junit5

import com.github.awstest.AwsClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsAsyncClient
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsClient

@ExtendWith(DynamoDbClientParameterResolver::class)
class DynamoDbClientParameterResolverTest {
    @Test
    fun `resolve DynamoDB client as an argument`(@AwsClient client: DynamoDbClient) {
        assertThat(client).isNotNull()
    }

    @Test
    fun `resolve DynamoDB async client as an argument`(@AwsClient client: DynamoDbClient) {
        assertThat(client).isNotNull()
    }

    @Test
    fun `resolve DynamoDB Streams client as an argument`(@AwsClient client: DynamoDbStreamsClient) {
        assertThat(client).isNotNull()
    }

    @Test
    fun `resolve DynamoDB Streams async client as an argument`(@AwsClient client: DynamoDbStreamsAsyncClient) {
        assertThat(client).isNotNull()
    }
}
