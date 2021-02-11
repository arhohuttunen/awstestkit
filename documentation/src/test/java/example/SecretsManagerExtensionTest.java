package example;

import com.github.awstestkit.AwsClient;
import com.github.awstestkit.localstack.junit5.LocalStackTest;
import com.github.awstestkit.secretsmanager.junit5.Secret;
import com.github.awstestkit.secretsmanager.junit5.SecretsManagerSetup;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.localstack.LocalStackContainer;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

import static org.junit.jupiter.api.Assertions.assertFalse;

// tag::user_guide[]
@LocalStackTest(services = LocalStackContainer.Service.SECRETSMANAGER)
@SecretsManagerSetup(secrets = {
        @Secret(name = "SecretName", value = "SecretValue")
})
public class SecretsManagerExtensionTest {
    @Test
    void setupSecrets(@AwsClient SecretsManagerClient client) {
        assertFalse(client.listSecrets().secretList().isEmpty());
    }
}
// end::user_guide[]
