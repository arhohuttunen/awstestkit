package com.github.awstest.cloudformation.junit5

import com.github.awstest.AwsClientFactory
import com.github.awstest.SdkClientParameterResolver
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.cloudformation.CloudFormationAsyncClient
import software.amazon.awssdk.services.cloudformation.CloudFormationClient
import kotlin.reflect.KClass

class CloudFormationClientParameterResolver : SdkClientParameterResolver() {
    override val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>>

    init {
        factories = mapOf(
            CloudFormationClient::class to CloudFormationClientFactory(CloudFormationClient.builder()),
            CloudFormationAsyncClient::class to CloudFormationAsyncClientFactory(CloudFormationAsyncClient.builder())
        )
    }
}
