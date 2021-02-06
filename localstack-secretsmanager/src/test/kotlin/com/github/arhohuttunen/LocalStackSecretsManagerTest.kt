package com.github.arhohuttunen

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient

@LocalStackTest(LocalStackContainer.Service.SECRETSMANAGER)
@LocalStackSecretsManager(secrets = [Secret(name = "SecretName", value = "SecretValue")])
class LocalStackSecretsManagerTest {
    @Test
    fun `secrets are created from class annotations`(@AwsClient secretsManagerClient: SecretsManagerClient) {
        val listSecretsResponse = secretsManagerClient.listSecrets()
        assertThat(listSecretsResponse.secretList()).isNotEmpty()
    }

    @Test
    @LocalStackSecretsManager(secrets = [Secret(name = "AnotherSecretName", value = "AnotherSecretValue")])
    fun `topics are created from method annotations`(@AwsClient secretsManagerClient: SecretsManagerClient) {
        val listSecretsResponse = secretsManagerClient.listSecrets()
        assertThat(listSecretsResponse.secretList()).hasSize(2)
    }
}