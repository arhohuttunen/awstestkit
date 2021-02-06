package com.github.arhohuttunen

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import software.amazon.awssdk.services.cloudformation.CloudFormationClient

@LocalStackTest(LocalStackContainer.Service.CLOUDFORMATION)
@LocalStackCloudFormation(Stack("stack", "/template.yml"))
class LocalStackCloudFormationTest {
    @Test
    fun `stacks are created from class annotations`(@AwsClient cloudFormationClient: CloudFormationClient) {
        val response = cloudFormationClient.describeStacks()
        assertThat(response.stacks()).isNotEmpty()
    }

    @Test
    @LocalStackCloudFormation(Stack("second-stack", "/template.yml"))
    fun `stacks are created from method annotations`(@AwsClient cloudFormationClient: CloudFormationClient) {
        val response = cloudFormationClient.describeStacks()
        assertThat(response.stacks()).hasSize(2)
    }
}