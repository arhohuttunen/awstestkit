package com.github.awstest

import org.junit.jupiter.api.extension.ExtensionContext

interface EndpointResolver {
    fun resolveEndpoint(extensionContext: ExtensionContext): Endpoint
}
