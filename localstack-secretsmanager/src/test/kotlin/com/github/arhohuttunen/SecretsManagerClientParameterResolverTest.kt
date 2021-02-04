package com.github.arhohuttunen

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.testcontainers.containers.localstack.LocalStackContainer
import software.amazon.awssdk.services.secretsmanager.SecretsManagerAsyncClient
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient

@LocalStackTest(services = [LocalStackContainer.Service.SECRETSMANAGER])
@ExtendWith(SecretsManagerClientParameterResolver::class)
class SecretsManagerClientParameterResolverTest {
    @Test
    fun `resolve SecretsManager client as an argument`(@AwsClient secretsManagerClient: SecretsManagerClient) {
        assertThat(secretsManagerClient).isNotNull()
    }

    @Test
    fun `resolve SecretsManager async client as an argument`(@AwsClient secretsManagerAsyncClient: SecretsManagerAsyncClient) {
        assertThat(secretsManagerAsyncClient).isNotNull()
    }
}