package com.github.arhohuttunen

import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import software.amazon.awssdk.services.secretsmanager.model.CreateSecretRequest
import software.amazon.awssdk.services.secretsmanager.model.DeleteSecretRequest

class SimpleSecretsManagerClient(private val secretsManagerClient: SecretsManagerClient) {
    fun createSecret(name: String, value: String) {
        val request = CreateSecretRequest.builder()
            .name(name)
            .secretString(value)
            .build()
        secretsManagerClient.createSecret(request)
    }

    fun deleteSecret(name: String) {
        val request = DeleteSecretRequest.builder()
            .secretId(name)
            .build()
        secretsManagerClient.deleteSecret(request)
    }
}