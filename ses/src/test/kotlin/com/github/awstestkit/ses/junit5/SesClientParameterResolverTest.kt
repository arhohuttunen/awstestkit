package com.github.awstestkit.ses.junit5

import com.github.awstestkit.AwsClient
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.ses.SesAsyncClient
import software.amazon.awssdk.services.ses.SesClient

@ExtendWith(SesClientParameterResolver::class)
class SesClientParameterResolverTest {
    @Test
    fun `resolve SES client as an argument`(@AwsClient client: SesClient) {
        client shouldNotBe null
    }

    @Test
    fun `resolve SES async client as an argument`(@AwsClient client: SesAsyncClient) {
        client shouldNotBe null
    }
}
