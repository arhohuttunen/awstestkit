package com.github.awstest.ses.junit5

import com.github.awstest.AwsClientFactory
import software.amazon.awssdk.services.ses.SesAsyncClient
import software.amazon.awssdk.services.ses.SesAsyncClientBuilder
import software.amazon.awssdk.services.ses.SesClient
import software.amazon.awssdk.services.ses.SesClientBuilder

typealias SesClientFactory = AwsClientFactory<SesClientBuilder, SesClient>
typealias SesAsyncClientFactory = AwsClientFactory<SesAsyncClientBuilder, SesAsyncClient>
