package com.github.awstest.kinesis.junit5

import com.github.awstest.AwsClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient
import software.amazon.awssdk.services.kinesis.KinesisClient

@ExtendWith(KinesisClientParameterResolver::class)
class KinesisClientParameterResolverTest {
    @Test
    fun `resolve Kinesis client as an argument`(@AwsClient client: KinesisClient) {
        assertThat(client).isNotNull()
    }

    @Test
    fun `resolve Kinesis async client as an argument`(@AwsClient client: KinesisAsyncClient) {
        assertThat(client).isNotNull()
    }
}