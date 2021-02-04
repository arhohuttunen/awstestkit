package com.github.arhohuttunen

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import software.amazon.awssdk.services.sns.SnsClient

@LocalStackTest(services = [LocalStackContainer.Service.SNS])
@LocalStackSns(topicNames = ["Topic"])
class LocalStackSnsTest {
    @Test
    fun `topics are created`(@AwsClient snsClient: SnsClient) {
        val listTopicsResponse = snsClient.listTopics()
        assertThat(listTopicsResponse.topics()).isNotEmpty()
    }

    @Test
    @LocalStackSns(topicNames = ["AnotherTopic"])
    fun `topics are created from methods`(@AwsClient snsClient: SnsClient) {
        val listTopicsResponse = snsClient.listTopics()
        assertThat(listTopicsResponse.topics()).hasSize(2)
    }
}