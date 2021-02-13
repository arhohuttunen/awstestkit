package com.github.awstestkit.cloudformation.junit5

import com.github.awstestkit.AwsClientFactory
import com.github.awstestkit.SdkClientFactory
import com.github.awstestkit.SdkClientParameterResolver
import org.junit.jupiter.api.extension.ExtensionContext
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.cloudformation.CloudFormationAsyncClient
import software.amazon.awssdk.services.cloudformation.CloudFormationClient
import kotlin.reflect.KClass

class CloudFormationClientParameterResolver : SdkClientParameterResolver() {
    private val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>>

    init {
        factories = mapOf(
            CloudFormationClient::class to CloudFormationClientFactory(CloudFormationClient.builder()),
            CloudFormationAsyncClient::class to CloudFormationAsyncClientFactory(CloudFormationAsyncClient.builder())
        )
    }

    override fun isSupported(type: KClass<out Any>): Boolean {
        return factories.containsKey(type)
    }

    override fun create(type: KClass<out Any>, extensionContext: ExtensionContext): SdkClient {
        return factories[type]!!.create(extensionContext)
    }
}
