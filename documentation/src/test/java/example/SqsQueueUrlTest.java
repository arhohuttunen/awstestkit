package example;

import com.github.awstestkit.AwsClient;
import com.github.awstestkit.localstack.junit5.LocalStackTest;
import com.github.awstestkit.sqs.junit5.QueueUrl;
import com.github.awstestkit.sqs.junit5.SqsQueue;
import com.github.awstestkit.sqs.junit5.SqsTest;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueAttributesRequest;
import software.amazon.awssdk.services.sqs.model.QueueAttributeName;

import static org.junit.jupiter.api.Assertions.assertFalse;

// tag::user_guide[]
@LocalStackTest
@SqsTest
@SqsQueue("QueueName")
class SqsQueueUrlTest {
    @Test
    void setupQueues(@AwsClient SqsClient client, @QueueUrl("QueueName") String queueUrl) {
        GetQueueAttributesRequest request = GetQueueAttributesRequest.builder()
                .queueUrl(queueUrl)
                .attributeNames(QueueAttributeName.ALL)
                .build();
        assertFalse(client.getQueueAttributes(request).attributes().isEmpty());
    }
}
// end::user_guide[]
