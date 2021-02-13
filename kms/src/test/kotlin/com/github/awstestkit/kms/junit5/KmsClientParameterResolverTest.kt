package com.github.awstestkit.kms.junit5

import com.github.awstestkit.AwsClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.kms.KmsAsyncClient
import software.amazon.awssdk.services.kms.KmsClient

@ExtendWith(KmsClientParameterResolver::class)
class KmsClientParameterResolverTest {
    @Test
    fun `resolve KMS client as an argument`(@AwsClient client: KmsClient) {
        assertThat(client).isNotNull()
    }

    @Test
    fun `resolve KMS async client as an argument`(@AwsClient client: KmsAsyncClient) {
        assertThat(client).isNotNull()
    }
}
