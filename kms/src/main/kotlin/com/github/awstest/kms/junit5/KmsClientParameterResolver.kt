package com.github.awstest.kms.junit5

import com.github.awstest.AwsClientFactory
import com.github.awstest.SdkClientParameterResolver
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.kms.KmsAsyncClient
import software.amazon.awssdk.services.kms.KmsAsyncClientBuilder
import software.amazon.awssdk.services.kms.KmsClient
import software.amazon.awssdk.services.kms.KmsClientBuilder
import kotlin.reflect.KClass

class KmsClientParameterResolver : SdkClientParameterResolver() {
    override val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>>

    init {
        factories = mapOf(
            KmsClient::class to AwsClientFactory<KmsClientBuilder, KmsClient>(KmsClient.builder()),
            KmsAsyncClient::class to AwsClientFactory<KmsAsyncClientBuilder, KmsAsyncClient>(KmsAsyncClient.builder())
        )
    }
}