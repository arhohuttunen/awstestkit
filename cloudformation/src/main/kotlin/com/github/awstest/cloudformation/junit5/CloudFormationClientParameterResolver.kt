package com.github.awstest.cloudformation.junit5

import com.github.awstest.AwsClientFactory
import com.github.awstest.SdkClientParameterResolver
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.cloudformation.CloudFormationAsyncClient
import software.amazon.awssdk.services.cloudformation.CloudFormationAsyncClientBuilder
import software.amazon.awssdk.services.cloudformation.CloudFormationClient
import software.amazon.awssdk.services.cloudformation.CloudFormationClientBuilder
import kotlin.reflect.KClass

class CloudFormationClientParameterResolver : SdkClientParameterResolver() {
    override val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>>

    init {
        factories = mapOf(
            CloudFormationClient::class to AwsClientFactory<CloudFormationClientBuilder, CloudFormationClient>(
                CloudFormationClient.builder()
            ),
            CloudFormationAsyncClient::class to AwsClientFactory<CloudFormationAsyncClientBuilder, CloudFormationAsyncClient>(
                CloudFormationAsyncClient.builder()
            )
        )
    }
}