package com.github.arhohuttunen

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import software.amazon.awssdk.services.sqs.SqsClient

@LocalStackTest(LocalStackContainer.Service.SQS)
@LocalStackSqs(queueNames = ["Queue"])
class LocalStackSqsTest {
    @Test
    fun `queues are created from class annotations`(@AwsClient sqsClient: SqsClient) {
        val listQueuesResponse = sqsClient.listQueues()
        assertThat(listQueuesResponse.queueUrls()).isNotEmpty()
    }

    @Test
    @LocalStackSqs(queueNames = ["AnotherQueue"])
    fun `queues are created from method annotations`(@AwsClient sqsClient: SqsClient) {
        val listQueuesResponse = sqsClient.listQueues()
        assertThat(listQueuesResponse.queueUrls()).hasSize(2)
    }
}