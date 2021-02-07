package com.github.awstest.firehose.junit5

import com.github.awstest.AwsClient
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.firehose.FirehoseClient

@ExtendWith(FirehoseClientParameterResolver::class)
class FirehoseClientParameterResolverTest {
    @Test
    fun `resolve Firehose client as an argument`(@AwsClient client: FirehoseClient) {
        Assertions.assertThat(client).isNotNull()
    }

    @Test
    fun `resolve Firehose async client as an argument`(@AwsClient client: FirehoseClient) {
        Assertions.assertThat(client).isNotNull()
    }
}
