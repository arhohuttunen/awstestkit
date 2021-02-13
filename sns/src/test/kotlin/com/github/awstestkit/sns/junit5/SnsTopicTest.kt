package com.github.awstestkit.sns.junit5

import com.github.awstestkit.AwsClient
import com.github.awstestkit.localstack.junit5.LocalStackTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import software.amazon.awssdk.services.sns.SnsClient

@LocalStackTest
@SnsTest
@SnsTopics(SnsTopic("Topic"))
class SnsTopicTest {
    @Test
    fun `topics are created from class annotations`(@AwsClient snsClient: SnsClient) {
        val listTopicsResponse = snsClient.listTopics()
        assertThat(listTopicsResponse.topics()).isNotEmpty()
    }

    @Test
    @SnsTopic("AnotherTopic")
    fun `topics are created from method annotations`(@AwsClient snsClient: SnsClient) {
        val listTopicsResponse = snsClient.listTopics()
        assertThat(listTopicsResponse.topics()).hasSize(2)
    }
}
