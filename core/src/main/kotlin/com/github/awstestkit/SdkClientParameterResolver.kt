package com.github.awstestkit

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import software.amazon.awssdk.core.SdkClient
import java.util.Optional

abstract class SdkClientParameterResolver : SdkClientFactory, ParameterResolver {
    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        val annotation: Optional<AwsClient> = parameterContext.findAnnotation(AwsClient::class.java)
        return annotation.isPresent && isSupported(parameterContext.parameter.type.kotlin)
    }

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): SdkClient {
        return create(parameterContext.parameter.type.kotlin, extensionContext)
    }
}
