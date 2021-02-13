package example;

import com.github.awstestkit.AwsClient;
import com.github.awstestkit.dynamodb.junit5.DynamoDbAttributeDefinition;
import com.github.awstestkit.dynamodb.junit5.DynamoDbKeySchemaElement;
import com.github.awstestkit.dynamodb.junit5.DynamoDbTable;
import com.github.awstestkit.dynamodb.junit5.DynamoDbTables;
import com.github.awstestkit.dynamodb.junit5.DynamoDbTest;
import com.github.awstestkit.localstack.junit5.LocalStackTest;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;

import static org.junit.jupiter.api.Assertions.assertFalse;

// tag::user_guide[]
@LocalStackTest
@DynamoDbTest
@DynamoDbTables(
        @DynamoDbTable(
                tableName = "table",
                keySchema = @DynamoDbKeySchemaElement(
                        attributeName = "id",
                        keyType = KeyType.HASH
                ),
                attributeDefinitions = @DynamoDbAttributeDefinition(
                        attributeName = "id",
                        attributeType = ScalarAttributeType.S
                )
        )
)
class DynamoDbExtensionTest {
    @Test
    void setupTables(@AwsClient DynamoDbClient client) {
        assertFalse(client.listTables().tableNames().isEmpty());
    }
}
// end::user_guide[]
