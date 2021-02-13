package example;

import com.github.awstestkit.AwsClient;
import com.github.awstestkit.localstack.junit5.LocalStackTest;
import com.github.awstestkit.sns.junit5.SnsTest;
import com.github.awstestkit.sns.junit5.SnsTopic;
import com.github.awstestkit.sns.junit5.TopicArn;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.GetTopicAttributesRequest;

import static org.junit.jupiter.api.Assertions.assertTrue;

// tag::user_guide[]
@LocalStackTest
@SnsTest
@SnsTopic("TopicName")
class SnsTopicArnTest {
    @Test
    void setupTopics(@AwsClient SnsClient client, @TopicArn("TopicName") String topicArn) {
        GetTopicAttributesRequest request = GetTopicAttributesRequest.builder()
                .topicArn(topicArn)
                .build();
        assertTrue(client.getTopicAttributes(request).hasAttributes());
    }
}
// end::user_guide[]
