package com.github.awstest.cloudformation.junit5

import com.github.awstest.AwsClientFactory
import software.amazon.awssdk.services.cloudformation.CloudFormationAsyncClient
import software.amazon.awssdk.services.cloudformation.CloudFormationAsyncClientBuilder
import software.amazon.awssdk.services.cloudformation.CloudFormationClient
import software.amazon.awssdk.services.cloudformation.CloudFormationClientBuilder

typealias CloudFormationClientFactory =
        AwsClientFactory<CloudFormationClientBuilder, CloudFormationClient>
typealias CloudFormationAsyncClientFactory =
        AwsClientFactory<CloudFormationAsyncClientBuilder, CloudFormationAsyncClient>
