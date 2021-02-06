package com.github.arhohuttunen

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import software.amazon.awssdk.services.dynamodb.DynamoDbClient

@LocalStackTest(services = [LocalStackContainer.Service.SNS])
@LocalStackDynamoDb
class DynamoDbClientParameterResolverTest {
    @Test
    fun `resolve DynamoDB client as an argument`(@AwsClient dymamoDbClient: DynamoDbClient) {
        assertThat(dymamoDbClient).isNotNull()
    }

    @Test
    fun `resolve DynamoDB async client as an argument`(@AwsClient dymamoDbAsyncClient: DynamoDbAsyncClient) {
        assertThat(dymamoDbAsyncClient).isNotNull()
    }
}