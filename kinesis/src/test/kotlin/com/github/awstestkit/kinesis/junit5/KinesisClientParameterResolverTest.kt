package com.github.awstestkit.kinesis.junit5

import com.github.awstestkit.AwsClient
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient
import software.amazon.awssdk.services.kinesis.KinesisClient

@ExtendWith(KinesisClientParameterResolver::class)
class KinesisClientParameterResolverTest {
    @Test
    fun `resolve Kinesis client as an argument`(@AwsClient client: KinesisClient) {
        client shouldNotBe null
    }

    @Test
    fun `resolve Kinesis async client as an argument`(@AwsClient client: KinesisAsyncClient) {
        client shouldNotBe null
    }
}
