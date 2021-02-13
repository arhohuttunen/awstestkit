package com.github.awstestkit.ses.junit5

import com.github.awstestkit.AwsClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.ses.SesAsyncClient
import software.amazon.awssdk.services.ses.SesClient

@ExtendWith(SesClientParameterResolver::class)
class SesClientParameterResolverTest {
    @Test
    fun `resolve SES client as an argument`(@AwsClient sesClient: SesClient) {
        assertThat(sesClient).isNotNull()
    }

    @Test
    fun `resolve SES async client as an argument`(@AwsClient sesAsyncClient: SesAsyncClient) {
        assertThat(sesAsyncClient).isNotNull()
    }
}
