package com.github.awstestkit.kms.junit5

import com.github.awstestkit.AwsClient
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.kms.KmsAsyncClient
import software.amazon.awssdk.services.kms.KmsClient

@ExtendWith(KmsClientParameterResolver::class)
class KmsClientParameterResolverTest {
    @Test
    fun `resolve KMS client as an argument`(@AwsClient client: KmsClient) {
        client shouldNotBe null
    }

    @Test
    fun `resolve KMS async client as an argument`(@AwsClient client: KmsAsyncClient) {
        client shouldNotBe null
    }
}
