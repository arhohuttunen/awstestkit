package com.github.awstest.secretsmanager.junit5

import com.github.awstest.AwsClientFactory
import com.github.awstest.SdkClientParameterResolver
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
