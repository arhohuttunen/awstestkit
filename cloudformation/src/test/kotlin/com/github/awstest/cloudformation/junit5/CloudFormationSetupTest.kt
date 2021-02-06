package com.github.awstest.cloudformation.junit5

import com.github.awstest.AwsClient
import com.github.awstest.localstack.junit5.LocalStackTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import software.amazon.awssdk.services.cloudformation.CloudFormationClient

@LocalStackTest(LocalStackContainer.Service.CLOUDFORMATION)
@CloudFormationSetup(CfnStack("stack", "/template.yml"))
class CloudFormationSetupTest {
    @Test
    fun `stacks are created from class annotations`(@AwsClient cloudFormationClient: CloudFormationClient) {
        val response = cloudFormationClient.describeStacks()
        assertThat(response.stacks()).isNotEmpty()
    }

    @Test
    @CloudFormationSetup(CfnStack("second-stack", "/template.yml"))
    fun `stacks are created from method annotations`(@AwsClient cloudFormationClient: CloudFormationClient) {
        val response = cloudFormationClient.describeStacks()
        assertThat(response.stacks()).hasSize(2)
    }
}