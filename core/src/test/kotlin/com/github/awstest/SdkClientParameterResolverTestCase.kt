package com.github.awstest

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider
import software.amazon.awssdk.awscore.client.builder.AwsClientBuilder
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration
import software.amazon.awssdk.regions.Region
import java.net.URI
import kotlin.reflect.KClass

@TestKitTestCase
@ExtendWith(SdkClientParameterResolverTestCase.DummyClientParameterResolver::class)
class SdkClientParameterResolverTestCase {

    @Test
    fun resolveClientParameterSuccessfully(@AwsClient dummyClient: DummyClient) {

    }

    @Test
    fun failWhenClientNotAnnotated(dummyClient: DummyClient) {

    }

    @Test
    fun failWithUnsupportedClients(@AwsClient unsupportedClient: String) {

    }

    class DummyClient : SdkClient {
        override fun close() {}
        override fun serviceName(): String = "DummyService"
    }

    class DummyClientBuilder : AwsClientBuilder<DummyClientBuilder, DummyClient> {
        override fun build(): DummyClient {
            return DummyClient()
        }

        override fun overrideConfiguration(overrideConfiguration: ClientOverrideConfiguration): DummyClientBuilder =
            this

        override fun endpointOverride(endpointOverride: URI): DummyClientBuilder = this
        override fun credentialsProvider(credentialsProvider: AwsCredentialsProvider): DummyClientBuilder = this
        override fun region(region: Region): DummyClientBuilder = this
    }

    class DummyClientParameterResolver : SdkClientParameterResolver() {
        override val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>> = mapOf(
            DummyClient::class to AwsClientFactory(DummyClientBuilder())
        )
    }
}