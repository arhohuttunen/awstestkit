package com.github.arhohuttunen

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import org.junit.platform.commons.util.AnnotationUtils
import software.amazon.awssdk.core.SdkClient
import java.util.Optional
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

abstract class SdkClientParameterResolver : ParameterResolver {
    protected abstract val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, *>>

    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        val annotation: Optional<AwsClient> = parameterContext.findAnnotation(AwsClient::class.java)

        return annotation.isPresent && factories.containsKey(parameterContext.parameter.type.kotlin)
    }

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): SdkClient? {
        return createClient(parameterContext.parameter.type.kotlin, extensionContext)
    }

    fun createClient(clientClass: KClass<out Any>, extensionContext: ExtensionContext): SdkClient? {
        val endpoint = resolveEndpoint(extensionContext)
        return factories[clientClass]?.create(endpoint)
    }

    private fun resolveEndpoint(extensionContext: ExtensionContext): Endpoint {
        val requiredClass = extensionContext.requiredTestClass
        val annotation: Optional<AwsEndpoint> = AnnotationUtils.findAnnotation(requiredClass, AwsEndpoint::class.java)

        return if (annotation.isPresent) {
            annotation.get().endpointResolver.createInstance().resolveEndpoint(extensionContext)
        } else {
            DefaultEndpoint()
        }
    }
}
