package com.github.awstestkit.cloudformation.junit5

import com.github.awstestkit.AwsClient
import com.github.awstestkit.localstack.junit5.LocalStackTest
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import org.junit.jupiter.api.Test
import software.amazon.awssdk.services.cloudformation.CloudFormationClient

@LocalStackTest
@CloudFormationTest
@CfnStacks(
    CfnStack("stack", "src/test/resources/template.yml")
)
class CfnStacksTest {
    @Test
    fun `stacks are created from class annotations`(@AwsClient cloudFormationClient: CloudFormationClient) {
        val response = cloudFormationClient.describeStacks()
        response.stacks().shouldNotBeEmpty()
    }

    @Test
    @CfnStack("second-stack", "src/test/resources/template.yml")
    fun `stacks are created from method annotations`(@AwsClient cloudFormationClient: CloudFormationClient) {
        val response = cloudFormationClient.describeStacks()
        response.stacks().shouldHaveSize(2)
    }
}
