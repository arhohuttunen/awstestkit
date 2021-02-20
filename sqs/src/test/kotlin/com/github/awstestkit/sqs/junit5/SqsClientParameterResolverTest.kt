package com.github.awstestkit.sqs.junit5

import com.github.awstestkit.AwsClient
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.SqsClient

@ExtendWith(SqsClientParameterResolver::class)
class SqsClientParameterResolverTest {
    @Test
    fun `resolve SQS client as an argument`(@AwsClient client: SqsClient) {
        client shouldNotBe null
    }

    @Test
    fun `resolve SQS async client as an argument`(@AwsClient client: SqsAsyncClient) {
        client shouldNotBe null
    }
}
