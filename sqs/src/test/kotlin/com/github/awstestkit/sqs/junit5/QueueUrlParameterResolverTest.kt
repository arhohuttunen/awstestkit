package com.github.awstestkit.sqs.junit5

import com.github.awstestkit.AwsClient
import com.github.awstestkit.localstack.junit5.LocalStackTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.GetQueueAttributesRequest

@LocalStackTest
@SqsTest
class QueueUrlParameterResolverTest {
    @Test
    @SqsQueue("Queue")
    fun `resolve queue URL`(@AwsClient client: SqsClient, @QueueUrl("Queue") queueUrl: String) {
        val request = GetQueueAttributesRequest.builder()
            .queueUrl(queueUrl)
            .build()
        assertThat(client.getQueueAttributes(request).attributes()).isNotEmpty()
    }
}
