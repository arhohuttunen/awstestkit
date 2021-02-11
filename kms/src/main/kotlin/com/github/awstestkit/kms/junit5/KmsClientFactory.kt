package com.github.awstestkit.kms.junit5

import com.github.awstestkit.AwsClientFactory
import software.amazon.awssdk.services.kms.KmsAsyncClient
import software.amazon.awssdk.services.kms.KmsAsyncClientBuilder
import software.amazon.awssdk.services.kms.KmsClient
import software.amazon.awssdk.services.kms.KmsClientBuilder

typealias KmsClientFactory = AwsClientFactory<KmsClientBuilder, KmsClient>
typealias KmsAsyncClientFactory = AwsClientFactory<KmsAsyncClientBuilder, KmsAsyncClient>
