package com.github.awstest.s3.junit5

import com.github.awstest.AwsClientFactory
import com.github.awstest.SdkClientParameterResolver
import software.amazon.awssdk.core.SdkClient
import software.amazon.awssdk.services.s3.S3AsyncClient
import software.amazon.awssdk.services.s3.S3AsyncClientBuilder
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.S3ClientBuilder
import kotlin.reflect.KClass

class S3ClientParameterResolver : SdkClientParameterResolver() {
    override val factories: Map<KClass<out SdkClient>, AwsClientFactory<*, *>>

    init {
        factories = mapOf(
            S3Client::class to AwsClientFactory<S3ClientBuilder, S3Client>(S3Client.builder()),
            S3AsyncClient::class to AwsClientFactory<S3AsyncClientBuilder, S3AsyncClient>(S3AsyncClient.builder())
        )
    }
}