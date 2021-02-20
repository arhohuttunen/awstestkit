package com.github.awstestkit.sqs.junit5

import com.github.awstestkit.AwsClient
import com.github.awstestkit.localstack.junit5.LocalStackTest
import io.kotest.matchers.maps.shouldNotBeEmpty
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
        val result = client.getQueueAttributes(request)
        result.attributes().shouldNotBeEmpty()
    }
}
