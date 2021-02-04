package com.github.arhohuttunen

import software.amazon.awssdk.services.sns.SnsClient
import software.amazon.awssdk.services.sns.model.CreateTopicRequest
import software.amazon.awssdk.services.sns.model.CreateTopicResponse
import software.amazon.awssdk.services.sns.model.DeleteTopicRequest

class SimpleSnsClient(private val snsClient: SnsClient) {
    fun createTopic(topicName: String): CreateTopicResponse {
        val request = CreateTopicRequest.builder()
            .name(topicName)
            .build()
        return snsClient.createTopic(request)
    }

    fun deleteTopic(topicName: String) {
        val request = DeleteTopicRequest.builder()
            .topicArn(createTopic(topicName).topicArn())
            .build()
        snsClient.deleteTopic(request)
    }
}