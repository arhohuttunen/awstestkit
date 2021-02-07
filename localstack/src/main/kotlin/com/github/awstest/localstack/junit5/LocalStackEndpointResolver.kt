package com.github.awstest.localstack.junit5

import com.github.awstest.Endpoint
import com.github.awstest.EndpointResolver
import com.github.awstest.localstack.LocalStackEndpoint
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ExtensionContextException
import org.testcontainers.containers.localstack.LocalStackContainer

class LocalStackEndpointResolver : EndpointResolver {
    private val namespace = ExtensionContext.Namespace.create(NAMESPACE)

    override fun resolveEndpoint(extensionContext: ExtensionContext): Endpoint {
        val container = extensionContext.getStore(namespace).get("container", LocalStackContainer::class.java) ?:
            throw ExtensionContextException("Localstack container not initialized")
        return LocalStackEndpoint(container)
    }
}
