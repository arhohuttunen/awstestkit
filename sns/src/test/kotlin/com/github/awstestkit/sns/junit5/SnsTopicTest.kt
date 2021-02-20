package com.github.awstestkit.sns.junit5

import com.github.awstestkit.AwsClient
import com.github.awstestkit.localstack.junit5.LocalStackTest
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import org.junit.jupiter.api.Test
import software.amazon.awssdk.services.sns.SnsClient

@LocalStackTest
@SnsTest
@SnsTopics(SnsTopic("Topic"))
class SnsTopicTest {
    @Test
    fun `topics are created from class annotations`(@AwsClient snsClient: SnsClient) {
        val listTopicsResponse = snsClient.listTopics()
        listTopicsResponse.topics().shouldNotBeEmpty()
    }

    @Test
    @SnsTopic("AnotherTopic")
    fun `topics are created from method annotations`(@AwsClient snsClient: SnsClient) {
        val listTopicsResponse = snsClient.listTopics()
        listTopicsResponse.topics().shouldHaveSize(2)
    }
}
