package com.github.awstest

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import software.amazon.awssdk.core.SdkClient
import java.util.Optional
import kotlin.reflect.KClass

abstract class SdkClientParameterResolver : ParameterResolver {
    protected abstract val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, out SdkClient>>

    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        val annotation: Optional<AwsClient> = parameterContext.findAnnotation(AwsClient::class.java)

        return annotation.isPresent && factories.containsKey(parameterContext.parameter.type.kotlin)
    }

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): SdkClient? {
        return factories[parameterContext.parameter.type.kotlin]?.create(extensionContext)
    }
}
