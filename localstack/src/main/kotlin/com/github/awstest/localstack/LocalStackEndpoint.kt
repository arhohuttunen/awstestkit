package com.github.awstest.localstack

import com.github.awstest.Endpoint
import org.testcontainers.containers.localstack.LocalStackContainer
import java.net.URI

class LocalStackEndpoint(private val container: LocalStackContainer) : Endpoint {
    override fun url(): URI {
        return container.getEndpointOverride(null)
    }

    override fun region(): String {
        return container.region
    }

    override fun accessKey(): String {
        return container.accessKey
    }

    override fun secretKey(): String {
        return container.secretKey
    }
}
