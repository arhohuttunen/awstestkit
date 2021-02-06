package com.github.awstest.junit5

import com.github.awstest.AwsClient
import com.github.awstest.localstack.junit5.LocalStackTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import software.amazon.awssdk.services.sns.SnsClient

@LocalStackTest(LocalStackContainer.Service.SNS)
@SnsSetup(topicNames = ["Topic"])
class SnsSetupTest {
    @Test
    fun `topics are created from class annotations`(@AwsClient snsClient: SnsClient) {
        val listTopicsResponse = snsClient.listTopics()
        assertThat(listTopicsResponse.topics()).isNotEmpty()
    }

    @Test
    @SnsSetup(topicNames = ["AnotherTopic"])
    fun `topics are created from method annotations`(@AwsClient snsClient: SnsClient) {
        val listTopicsResponse = snsClient.listTopics()
        assertThat(listTopicsResponse.topics()).hasSize(2)
    }
}