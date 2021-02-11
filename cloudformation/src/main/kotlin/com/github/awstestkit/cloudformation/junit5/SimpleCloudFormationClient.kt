package com.github.awstestkit.cloudformation.junit5

import software.amazon.awssdk.services.cloudformation.CloudFormationClient
import software.amazon.awssdk.services.cloudformation.model.CreateStackRequest
import software.amazon.awssdk.services.cloudformation.model.DeleteStackRequest
import java.io.File

class SimpleCloudFormationClient(private val cloudFormationClient: CloudFormationClient) {
    fun createStack(stackName: String, templateFilename: String) {
        val template = File(templateFilename).readText()
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
