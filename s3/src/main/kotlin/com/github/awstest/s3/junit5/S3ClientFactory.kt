package com.github.awstest.s3.junit5

import com.github.awstest.AwsClientFactory
import software.amazon.awssdk.services.s3.S3AsyncClient
import software.amazon.awssdk.services.s3.S3AsyncClientBuilder
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.S3ClientBuilder

typealias S3ClientFactory = AwsClientFactory<S3ClientBuilder, S3Client>
typealias S3AsyncClientFactory = AwsClientFactory<S3AsyncClientBuilder, S3AsyncClient>
