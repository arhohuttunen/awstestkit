package com.github.arhohuttunen

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.awscore.client.builder.AwsClientBuilder
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.regions.Region

class AwsClientFactory<T : AwsClientBuilder<T, U>, U : SdkClient>(
    private val awsClientBuilder: AwsClientBuilder<T, U>
) {
    fun create(endpoint: Endpoint): SdkClient {
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
}