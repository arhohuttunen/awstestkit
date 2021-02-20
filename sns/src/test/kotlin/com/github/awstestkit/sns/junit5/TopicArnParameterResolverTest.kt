package com.github.awstestkit.sns.junit5

import com.github.awstestkit.AwsClient
import com.github.awstestkit.localstack.junit5.LocalStackTest
import io.kotest.matchers.maps.shouldNotBeEmpty
import org.junit.jupiter.api.Test
import software.amazon.awssdk.services.sns.SnsClient
import software.amazon.awssdk.services.sns.model.GetTopicAttributesRequest

@LocalStackTest
@SnsTest
class TopicArnParameterResolverTest {
    @Test
    @SnsTopic("Topic")
    fun `resolve topic ARN`(@TopicArn("Topic") topicArn: String, @AwsClient client: SnsClient) {
        val request = GetTopicAttributesRequest.builder()
            .topicArn(topicArn)
            .build()
        val result = client.getTopicAttributes(request)
        result.attributes().shouldNotBeEmpty()
    }
}
