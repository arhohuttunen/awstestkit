package com.github.awstestkit.localstack

import com.github.awstestkit.localstack.junit5.LocalStackEndpointResolver
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test
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
            shouldThrow<ExtensionContextException> { endpointResolver.resolveEndpoint(context) }
        }
    }
}
