package com.github.awstestkit.s3.junit5

import com.github.awstestkit.AwsClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.s3.S3AsyncClient
import software.amazon.awssdk.services.s3.S3Client

@ExtendWith(S3ClientParameterResolver::class)
class S3ClientParameterResolverTest {
    @Test
    fun `resolve S3 client as an argument`(@AwsClient s3Client: S3Client) {
        assertThat(s3Client).isNotNull()
    }

    @Test
    fun `resolve S3 async client as an argument`(@AwsClient s3AsyncClient: S3AsyncClient) {
        assertThat(s3AsyncClient).isNotNull()
    }
}
