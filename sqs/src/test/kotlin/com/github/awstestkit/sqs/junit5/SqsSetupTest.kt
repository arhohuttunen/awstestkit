package com.github.awstestkit.sqs.junit5

import com.github.awstestkit.AwsClient
import com.github.awstestkit.localstack.junit5.LocalStackTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import software.amazon.awssdk.services.sqs.SqsClient

@LocalStackTest(LocalStackContainer.Service.SQS)
@SqsSetup(queueNames = ["Queue"])
class SqsSetupTest {
    @Test
    fun `queues are created from class annotations`(@AwsClient sqsClient: SqsClient) {
        val listQueuesResponse = sqsClient.listQueues()
        assertThat(listQueuesResponse.queueUrls()).isNotEmpty()
    }

    @Test
    @SqsSetup(queueNames = ["AnotherQueue"])
    fun `queues are created from method annotations`(@AwsClient sqsClient: SqsClient) {
        val listQueuesResponse = sqsClient.listQueues()
        assertThat(listQueuesResponse.queueUrls()).hasSize(2)
    }
}
