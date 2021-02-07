package com.github.awstest.ses.junit5

import com.github.awstest.AwsClient
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.ses.SesAsyncClient
import software.amazon.awssdk.services.ses.SesClient

@ExtendWith(SesClientParameterResolver::class)
class SesClientParameterResolverTest {
    @Test
    fun `resolve SES client as an argument`(@AwsClient sesClient: SesClient) {
        Assertions.assertThat(sesClient).isNotNull()
    }

    @Test
    fun `resolve SES async client as an argument`(@AwsClient sesAsyncClient: SesAsyncClient) {
        Assertions.assertThat(sesAsyncClient).isNotNull()
    }
}
