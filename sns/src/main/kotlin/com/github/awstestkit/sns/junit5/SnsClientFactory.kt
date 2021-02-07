package com.github.awstestkit.sns.junit5

import com.github.awstestkit.AwsClientFactory
import software.amazon.awssdk.services.sns.SnsAsyncClient
import software.amazon.awssdk.services.sns.SnsAsyncClientBuilder
import software.amazon.awssdk.services.sns.SnsClient
import software.amazon.awssdk.services.sns.SnsClientBuilder

typealias SnsClientFactory = AwsClientFactory<SnsClientBuilder, SnsClient>
typealias SnsAsyncClientFactory = AwsClientFactory<SnsAsyncClientBuilder, SnsAsyncClient>
