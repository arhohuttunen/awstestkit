package com.github.awstestkit.dynamodb.junit5

import com.github.awstestkit.AwsClient
import com.github.awstestkit.localstack.junit5.LocalStackTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.KeyType
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType

@LocalStackTest(LocalStackContainer.Service.DYNAMODB)
@DynamoDbTest
@DynamoDbTables(
    DynamoDbTable(
        tableName = "test-table",
        keySchema = [DynamoDbKeySchemaElement("id", KeyType.HASH)],
        attributeDefinitions = [DynamoDbAttributeDefinition("id", ScalarAttributeType.S)]
    )
)
class DynamoDbTablesTest {
    @Test
    fun `tables are created from class annotations`(@AwsClient dynamoDbClient: DynamoDbClient) {
        val tables = dynamoDbClient.listTables()
        assertThat(tables.tableNames()).isNotEmpty()
    }

    @Test
    @DynamoDbTable(
        tableName = "another-test-table",
        keySchema = [DynamoDbKeySchemaElement("id", KeyType.HASH)],
        attributeDefinitions = [DynamoDbAttributeDefinition("id", ScalarAttributeType.S)]
    )
    fun `tables are created from method annotations`(@AwsClient dynamoDbClient: DynamoDbClient) {
        val tables = dynamoDbClient.listTables()
        assertThat(tables.tableNames()).hasSize(2)
    }
}
