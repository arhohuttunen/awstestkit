package com.github.awstestkit.s3.junit5

import com.github.awstestkit.AwsClient
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.s3.S3AsyncClient
import software.amazon.awssdk.services.s3.S3Client

@ExtendWith(S3ClientParameterResolver::class)
class S3ClientParameterResolverTest {
    @Test
    fun `resolve S3 client as an argument`(@AwsClient client: S3Client) {
        client shouldNotBe null
    }

    @Test
    fun `resolve S3 async client as an argument`(@AwsClient client: S3AsyncClient) {
        client shouldNotBe null
    }
}
