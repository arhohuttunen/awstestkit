package com.github.awstestkit.sqs.junit5

import com.github.awstestkit.AwsClient
import com.github.awstestkit.localstack.junit5.LocalStackTest
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import org.junit.jupiter.api.Test
import software.amazon.awssdk.services.sqs.SqsClient

@LocalStackTest
@SqsTest
@SqsQueues(SqsQueue("Queue"))
class SqsQueueTest {
    @Test
    fun `queues are created from class annotations`(@AwsClient sqsClient: SqsClient) {
        val listQueuesResponse = sqsClient.listQueues()
        listQueuesResponse.queueUrls().shouldNotBeEmpty()
    }

    @Test
    @SqsQueue("AnotherQueue")
    fun `queues are created from method annotations`(@AwsClient sqsClient: SqsClient) {
        val listQueuesResponse = sqsClient.listQueues()
        listQueuesResponse.queueUrls().shouldHaveSize(2)
    }
}
