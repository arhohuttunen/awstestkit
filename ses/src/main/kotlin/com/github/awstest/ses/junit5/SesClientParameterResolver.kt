package com.github.awstest.ses.junit5

import com.github.awstest.AwsClientFactory
import com.github.awstest.SdkClientParameterResolver
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.ses.SesAsyncClient
import software.amazon.awssdk.services.ses.SesClient
import kotlin.reflect.KClass

class SesClientParameterResolver : SdkClientParameterResolver() {
    override val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>>

    init {
        factories = mapOf(
            SesClient::class to SesClientFactory(SesClient.builder()),
            SesAsyncClient::class to SesAsyncClientFactory(SesAsyncClient.builder())
        )
    }
}
