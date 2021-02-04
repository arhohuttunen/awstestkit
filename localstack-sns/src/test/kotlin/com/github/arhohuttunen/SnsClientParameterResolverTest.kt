package com.github.arhohuttunen

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.testcontainers.containers.localstack.LocalStackContainer
import software.amazon.awssdk.services.sns.SnsAsyncClient
import software.amazon.awssdk.services.sns.SnsClient

@LocalStackTest(services = [LocalStackContainer.Service.SNS])
@ExtendWith(SnsClientParameterResolver::class)
class SnsClientParameterResolverTest {
    @Test
    fun `resolve SNS client as an argument`(@AwsClient snsClient: SnsClient) {
        assertThat(snsClient).isNotNull()
    }

    @Test
    fun `resolve SNS async client as an argument`(@AwsClient snsAsyncClient: SnsAsyncClient) {
        assertThat(snsAsyncClient).isNotNull()
    }
}