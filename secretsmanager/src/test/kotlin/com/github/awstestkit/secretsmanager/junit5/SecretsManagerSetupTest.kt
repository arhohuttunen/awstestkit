package com.github.awstestkit.secretsmanager.junit5

import com.github.awstestkit.AwsClient
import com.github.awstestkit.localstack.junit5.LocalStackTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient

@LocalStackTest(LocalStackContainer.Service.SECRETSMANAGER)
@SecretsManagerSetup(secrets = [Secret(name = "SecretName", value = "SecretValue")])
class SecretsManagerSetupTest {
    @Test
    fun `secrets are created from class annotations`(@AwsClient secretsManagerClient: SecretsManagerClient) {
        val listSecretsResponse = secretsManagerClient.listSecrets()
        assertThat(listSecretsResponse.secretList()).isNotEmpty()
    }

    @Test
    @SecretsManagerSetup(secrets = [Secret(name = "AnotherSecretName", value = "AnotherSecretValue")])
    fun `topics are created from method annotations`(@AwsClient secretsManagerClient: SecretsManagerClient) {
        val listSecretsResponse = secretsManagerClient.listSecrets()
        assertThat(listSecretsResponse.secretList()).hasSize(2)
    }
}
