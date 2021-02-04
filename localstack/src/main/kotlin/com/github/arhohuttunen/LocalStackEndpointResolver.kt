package com.github.arhohuttunen

import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.localstack.LocalStackContainer

class LocalStackEndpointResolver : EndpointResolver {
    private val namespace = ExtensionContext.Namespace.create(NAMESPACE)

    override fun resolveEndpoint(extensionContext: ExtensionContext): Endpoint {
        return LocalStackEndpoint(extensionContext.getStore(namespace).get("container") as LocalStackContainer)
    }
}