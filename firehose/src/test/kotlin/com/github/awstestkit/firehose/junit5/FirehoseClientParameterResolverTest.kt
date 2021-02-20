package com.github.awstestkit.firehose.junit5

import com.github.awstestkit.AwsClient
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.firehose.FirehoseClient

@ExtendWith(FirehoseClientParameterResolver::class)
class FirehoseClientParameterResolverTest {
    @Test
    fun `resolve Firehose client as an argument`(@AwsClient client: FirehoseClient) {
        client shouldNotBe null
    }

    @Test
    fun `resolve Firehose async client as an argument`(@AwsClient client: FirehoseClient) {
        client shouldNotBe null
    }
}
