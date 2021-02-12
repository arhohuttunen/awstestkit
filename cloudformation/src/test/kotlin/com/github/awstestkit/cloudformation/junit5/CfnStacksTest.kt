package com.github.awstestkit.cloudformation.junit5

import com.github.awstestkit.AwsClient
import com.github.awstestkit.localstack.junit5.LocalStackTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import software.amazon.awssdk.services.cloudformation.CloudFormationClient

@LocalStackTest(LocalStackContainer.Service.CLOUDFORMATION)
@CloudFormationTest
@CfnStacks(
    CfnStack("stack", "src/test/resources/template.yml")
)
class CfnStacksTest {
    @Test
    fun `stacks are created from class annotations`(@AwsClient cloudFormationClient: CloudFormationClient) {
        val response = cloudFormationClient.describeStacks()
        assertThat(response.stacks()).isNotEmpty()
    }

    @Test
    @CfnStack("second-stack", "src/test/resources/template.yml")
    fun `stacks are created from method annotations`(@AwsClient cloudFormationClient: CloudFormationClient) {
        val response = cloudFormationClient.describeStacks()
        assertThat(response.stacks()).hasSize(2)
    }
}
