package com.github.awstest.secretsmanager.junit5

import com.github.awstest.AwsClientFactory
import software.amazon.awssdk.services.secretsmanager.SecretsManagerAsyncClient
import software.amazon.awssdk.services.secretsmanager.SecretsManagerAsyncClientBuilder
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClientBuilder

typealias SecretsManagerClientFactory =
        AwsClientFactory<SecretsManagerClientBuilder, SecretsManagerClient>
typealias SecretsManagerAsyncClientFactory =
        AwsClientFactory<SecretsManagerAsyncClientBuilder, SecretsManagerAsyncClient>
