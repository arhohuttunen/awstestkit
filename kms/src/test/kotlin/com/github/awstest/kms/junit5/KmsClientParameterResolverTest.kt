package com.github.awstest.kms.junit5

import com.github.awstest.AwsClient
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.kms.KmsAsyncClient
import software.amazon.awssdk.services.kms.KmsClient

@ExtendWith(KmsClientParameterResolver::class)
class KmsClientParameterResolverTest {
    @Test
    fun `resolve KMS client as an argument`(@AwsClient client: KmsClient) {
        Assertions.assertThat(client).isNotNull()
    }

    @Test
    fun `resolve KMS async client as an argument`(@AwsClient client: KmsAsyncClient) {
        Assertions.assertThat(client).isNotNull()
    }
}
