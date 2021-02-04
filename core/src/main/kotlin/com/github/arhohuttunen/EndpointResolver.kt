package com.github.arhohuttunen

import org.junit.jupiter.api.extension.ExtensionContext

interface EndpointResolver {
    fun resolveEndpoint(extensionContext: ExtensionContext): Endpoint
}