package com.github.arhohuttunen

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import software.amazon.awssdk.services.dynamodb.DynamoDbClient

@LocalStackTest(services = [LocalStackContainer.Service.DYNAMODB])
@LocalStackDynamoDb(tables = [DynamoDbTable("test-table", [DynamoDbTableKey("id")])])
class LocalStackDynamoDbTest {
    @Test
    fun `tables are created from class annotations`(@AwsClient dynamoDbClient: DynamoDbClient) {
        val tables = dynamoDbClient.listTables()
        assertThat(tables.tableNames()).isNotEmpty()
    }

    @Test
    @LocalStackDynamoDb(tables = [DynamoDbTable("another-test-table", [DynamoDbTableKey("id")])])
    fun `tables are created from method annotations`(@AwsClient dynamoDbClient: DynamoDbClient) {
        val tables = dynamoDbClient.listTables()
        assertThat(tables.tableNames()).hasSize(2)
    }
}