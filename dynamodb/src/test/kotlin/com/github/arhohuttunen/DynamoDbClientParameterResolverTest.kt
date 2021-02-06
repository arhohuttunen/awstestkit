package com.github.arhohuttunen

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.dynamodb.DynamoDbClient

@ExtendWith(DynamoDbClientParameterResolver::class)
class DynamoDbClientParameterResolverTest {
    @Test
    fun `resolve DynamoDB client as an argument`(@AwsClient dynamoDbClient: DynamoDbClient) {
        assertThat(dynamoDbClient).isNotNull()
    }

    @Test
    fun `resolve DynamoDB async client as an argument`(@AwsClient dynamoDbAsyncClient: DynamoDbClient) {
        assertThat(dynamoDbAsyncClient).isNotNull()
    }
}