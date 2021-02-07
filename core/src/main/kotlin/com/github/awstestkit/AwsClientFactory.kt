package com.github.awstestkit

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.util.AnnotationUtils
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.awscore.client.builder.AwsClientBuilder
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.regions.Region
import java.util.Optional
import kotlin.reflect.full.createInstance

class AwsClientFactory<T : AwsClientBuilder<T, U>, U : SdkClient>(
    private val awsClientBuilder: AwsClientBuilder<T, U>
) {
    fun create(extensionContext: ExtensionContext): U {
        val endpoint = resolveEndpoint(extensionContext)

        return awsClientBuilder
            .endpointOverride(endpoint.url())
            .region(Region.of(endpoint.region()))
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(endpoint.accessKey(), endpoint.secretKey())
                )
            )
            .build()
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
