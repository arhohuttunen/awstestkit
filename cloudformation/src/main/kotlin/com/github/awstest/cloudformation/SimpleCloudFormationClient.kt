package com.github.awstest.cloudformation

import software.amazon.awssdk.services.cloudformation.CloudFormationClient
import software.amazon.awssdk.services.cloudformation.model.CreateStackRequest
import software.amazon.awssdk.services.cloudformation.model.DeleteStackRequest

class SimpleCloudFormationClient(private val cloudFormationClient: CloudFormationClient) {
    fun createStack(stackName: String, templateFilename: String) {
        val template = SimpleCloudFormationClient::class.java.getResource(templateFilename).readText()
        val request = CreateStackRequest.builder()
            .stackName(stackName)
            .templateBody(template)
            .build()
        cloudFormationClient.createStack(request)
    }

    fun deleteStack(stackName: String) {
        val request = DeleteStackRequest.builder()
            .stackName(stackName)
            .build()
        cloudFormationClient.deleteStack(request)
    }
}