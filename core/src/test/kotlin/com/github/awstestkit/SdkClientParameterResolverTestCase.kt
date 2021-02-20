package com.github.awstestkit

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import software.amazon.awssdk.core.SdkClient
import kotlin.reflect.KClass

@TestKitTestCase
@ExtendWith(SdkClientParameterResolverTestCase.DummyClientParameterResolver::class)
class SdkClientParameterResolverTestCase {

    @Test
    fun resolveClientParameterSuccessfully(@AwsClient annotatedClient: DummyClient) {
        assertThat(annotatedClient).isNotNull()
    }

    @Test
    fun failWhenClientNotAnnotated(unannotatedClient: DummyClient) {
        assertThat(unannotatedClient).isNull()
    }

    @Test
    fun failWithUnsupportedClients(@AwsClient unsupportedClient: String) {
        assertThat(unsupportedClient).isNull()
    }

    class DummyClient : SdkClient {
        override fun close() = Unit
        override fun serviceName(): String = "DummyService"
    }

    class DummyClientParameterResolver : SdkClientParameterResolver() {
        override fun isSupported(type: KClass<out Any>): Boolean {
            return type == DummyClient::class
        }

        override fun create(type: KClass<out Any>, extensionContext: ExtensionContext): SdkClient {
            return DummyClient()
        }
    }
}
