package com.github.awstestkit.sns.junit5

import com.github.awstestkit.AwsClient
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.sns.SnsAsyncClient
import software.amazon.awssdk.services.sns.SnsClient

@ExtendWith(SnsClientParameterResolver::class)
class SnsClientParameterResolverTest {
    @Test
    fun `resolve SNS client as an argument`(@AwsClient client: SnsClient) {
        client shouldNotBe null
    }

    @Test
    fun `resolve SNS async client as an argument`(@AwsClient client: SnsAsyncClient) {
        client shouldNotBe null
    }
}
