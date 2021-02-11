package com.github.awstestkit.sns.junit5

import com.github.awstestkit.AwsClient
import com.github.awstestkit.localstack.junit5.LocalStackTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import software.amazon.awssdk.services.sns.SnsClient
import software.amazon.awssdk.services.sns.model.GetTopicAttributesRequest

@LocalStackTest(LocalStackContainer.Service.SNS)
@SnsTest
class TopicArnParameterResolverTest {
    @Test
    @SnsTopic("Topic")
    fun `resolve topic ARN`(
        @TopicArn("Topic") topicArn: String, @AwsClient client: SnsClient
    ) {
        val request = GetTopicAttributesRequest.builder()
            .topicArn(topicArn)
            .build()
        assertThat(client.getTopicAttributes(request).hasAttributes()).isTrue()
    }
}
