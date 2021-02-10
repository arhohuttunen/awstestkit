package com.github.awstestkit.sqs.junit5

import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest
import software.amazon.awssdk.services.sqs.model.CreateQueueResponse
import software.amazon.awssdk.services.sqs.model.DeleteQueueRequest
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest

class SimpleSqsClient(private val sqsClient: SqsClient) {
    fun createQueue(queueName: String): CreateQueueResponse {
        val createQueueRequest = CreateQueueRequest.builder()
            .queueName(queueName)
            .build()
        return sqsClient.createQueue(createQueueRequest)
    }

    fun getQueueUrl(queueName: String): String {
        val getQueueUrlRequest = GetQueueUrlRequest.builder()
            .queueName(queueName)
            .build()
        return sqsClient.getQueueUrl(getQueueUrlRequest).queueUrl()
    }

    fun deleteQueue(queueName: String) {
        val deleteQueueRequest = DeleteQueueRequest.builder()
            .queueUrl(getQueueUrl(queueName))
            .build()
        sqsClient.deleteQueue(deleteQueueRequest)
    }
}
