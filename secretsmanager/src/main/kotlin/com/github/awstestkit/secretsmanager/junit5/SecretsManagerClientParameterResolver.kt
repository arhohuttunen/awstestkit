package com.github.awstestkit.secretsmanager.junit5

import com.github.awstestkit.AwsClientFactory
import com.github.awstestkit.SdkClientParameterResolver
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.secretsmanager.SecretsManagerAsyncClient
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import kotlin.reflect.KClass

class SecretsManagerClientParameterResolver : SdkClientParameterResolver() {
    override val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>>

    init {
        factories = mapOf(
            SecretsManagerClient::class to SecretsManagerClientFactory(SecretsManagerClient.builder()),
            SecretsManagerAsyncClient::class to SecretsManagerAsyncClientFactory(SecretsManagerAsyncClient.builder())
        )
    }
}
