package com.github.awstestkit.firehose.junit5

import com.github.awstestkit.AwsClientFactory
import software.amazon.awssdk.services.firehose.FirehoseAsyncClient
import software.amazon.awssdk.services.firehose.FirehoseAsyncClientBuilder
import software.amazon.awssdk.services.firehose.FirehoseClient
import software.amazon.awssdk.services.firehose.FirehoseClientBuilder

typealias FirehoseClientFactory = AwsClientFactory<FirehoseClientBuilder, FirehoseClient>
typealias FirehoseAsyncClientFactory = AwsClientFactory<FirehoseAsyncClientBuilder, FirehoseAsyncClient>
