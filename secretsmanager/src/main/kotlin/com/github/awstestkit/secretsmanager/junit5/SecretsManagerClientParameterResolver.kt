package com.github.awstestkit.secretsmanager.junit5

import com.github.awstestkit.AwsClientFactory
import com.github.awstestkit.SdkClientParameterResolver
import org.junit.jupiter.api.extension.ExtensionContext
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.secretsmanager.SecretsManagerAsyncClient
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import kotlin.reflect.KClass

class SecretsManagerClientParameterResolver : SdkClientParameterResolver() {
    private val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>>

    init {
        factories = mapOf(
            SecretsManagerClient::class to SecretsManagerClientFactory(SecretsManagerClient.builder()),
            SecretsManagerAsyncClient::class to SecretsManagerAsyncClientFactory(SecretsManagerAsyncClient.builder())
        )
    }

    override fun isSupported(type: KClass<out Any>): Boolean {
        return factories.containsKey(type)
    }

    override fun create(type: KClass<out Any>, extensionContext: ExtensionContext): SdkClient {
        return factories[type]!!.create(extensionContext)
    }
}
