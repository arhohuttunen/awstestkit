package com.github.awstest.sqs.junit5

import com.github.awstest.AwsClientFactory
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.SqsAsyncClientBuilder
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.SqsClientBuilder

typealias SqsClientFactory = AwsClientFactory<SqsClientBuilder, SqsClient>
typealias SqsAsyncClientFactory = AwsClientFactory<SqsAsyncClientBuilder, SqsAsyncClient>
