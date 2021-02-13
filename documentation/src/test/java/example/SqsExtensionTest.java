package example;

import com.github.awstestkit.AwsClient;
import com.github.awstestkit.localstack.junit5.LocalStackTest;
import com.github.awstestkit.sqs.junit5.SqsQueue;
import com.github.awstestkit.sqs.junit5.SqsTest;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.sqs.SqsClient;

import static org.junit.jupiter.api.Assertions.assertFalse;

// tag::user_guide[]
@LocalStackTest
@SqsTest
@SqsQueue("QueueName")
class SqsExtensionTest {
    @Test
    void setupQueues(@AwsClient SqsClient client) {
        assertFalse(client.listQueues().queueUrls().isEmpty());
    }
}
// end::user_guide[]
