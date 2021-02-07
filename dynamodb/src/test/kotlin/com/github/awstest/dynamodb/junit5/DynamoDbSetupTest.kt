package com.github.awstest.dynamodb.junit5

import com.github.awstest.AwsClient
import com.github.awstest.localstack.junit5.LocalStackTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import software.amazon.awssdk.services.dynamodb.DynamoDbClient

@LocalStackTest(LocalStackContainer.Service.DYNAMODB)
@DynamoDbSetup(tables = [DynamoDbTable("test-table", [DynamoDbTableKey("id")])])
class DynamoDbSetupTest {
    @Test
    fun `tables are created from class annotations`(@AwsClient dynamoDbClient: DynamoDbClient) {
        val tables = dynamoDbClient.listTables()
        assertThat(tables.tableNames()).isNotEmpty()
    }

    @Test
    @DynamoDbSetup(tables = [DynamoDbTable("another-test-table", [DynamoDbTableKey("id")])])
    fun `tables are created from method annotations`(@AwsClient dynamoDbClient: DynamoDbClient) {
        val tables = dynamoDbClient.listTables()
        assertThat(tables.tableNames()).hasSize(2)
    }
}
