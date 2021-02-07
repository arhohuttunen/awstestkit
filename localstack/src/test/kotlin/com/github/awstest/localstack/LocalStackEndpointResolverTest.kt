package com.github.awstest.localstack

import com.github.awstest.localstack.junit5.LocalStackEndpointResolver
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ExtensionContextException

@ExtendWith(LocalStackEndpointResolverTest.ResolverExtension::class)
class LocalStackEndpointResolverTest {
    @Test
    fun `throw ExtensionContextException if container is not set`() {
        // Assert in lifecycle callback
    }

    class ResolverExtension : BeforeEachCallback {
        override fun beforeEach(context: ExtensionContext) {
            val endpointResolver = LocalStackEndpointResolver()
            assertThrows<ExtensionContextException> { endpointResolver.resolveEndpoint(context) }
        }
    }
}