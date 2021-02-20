package com.github.awstestkit.secretsmanager.junit5

import com.github.awstestkit.AwsClient
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.secretsmanager.SecretsManagerAsyncClient
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient

@ExtendWith(SecretsManagerClientParameterResolver::class)
class SecretsManagerClientParameterResolverTest {
    @Test
    fun `resolve SecretsManager client as an argument`(@AwsClient client: SecretsManagerClient) {
        client shouldNotBe null
    }

    @Test
    fun `resolve SecretsManager async client as an argument`(@AwsClient client: SecretsManagerAsyncClient) {
        client shouldNotBe null
    }
}
