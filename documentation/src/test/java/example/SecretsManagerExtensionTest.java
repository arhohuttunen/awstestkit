package example;

import com.github.awstestkit.AwsClient;
import com.github.awstestkit.localstack.junit5.LocalStackTest;
import com.github.awstestkit.secretsmanager.junit5.Secret;
import com.github.awstestkit.secretsmanager.junit5.Secrets;
import com.github.awstestkit.secretsmanager.junit5.SecretsManagerTest;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

import static org.junit.jupiter.api.Assertions.assertFalse;

// tag::user_guide[]
@LocalStackTest
@SecretsManagerTest
@Secrets(
        @Secret(name = "SecretName", value = "SecretValue")
)
public class SecretsManagerExtensionTest {
    @Test
    void setupSecrets(@AwsClient SecretsManagerClient client) {
        assertFalse(client.listSecrets().secretList().isEmpty());
    }
}
// end::user_guide[]
