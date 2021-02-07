package com.github.awstestkit.cloudformation.junit5

import com.github.awstestkit.AwsClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.cloudformation.CloudFormationAsyncClient
import software.amazon.awssdk.services.cloudformation.CloudFormationClient

@ExtendWith(CloudFormationClientParameterResolver::class)
class CloudFormationClientParameterResolverTest {
    @Test
    fun `resolve CloudFormation client as an argument`(@AwsClient cfClient: CloudFormationClient) {
        assertThat(cfClient).isNotNull()
    }

    @Test
    fun `resolve CloudFormation async client as an argument`(@AwsClient cfAsyncClient: CloudFormationAsyncClient) {
        assertThat(cfAsyncClient).isNotNull()
    }
}
