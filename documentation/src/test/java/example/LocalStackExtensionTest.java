package example;

import com.github.awstestkit.AwsClient;
import com.github.awstestkit.localstack.junit5.LocalStackTest;
import com.github.awstestkit.sns.junit5.SnsClientParameterResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testcontainers.containers.localstack.LocalStackContainer;
import software.amazon.awssdk.services.sns.SnsClient;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

// tag::user_guide[]
@LocalStackTest(services = LocalStackContainer.Service.SNS)
@ExtendWith(SnsClientParameterResolver.class)
class LocalStackExtensionTest {
    @Test
    void localStackIsRunning(@AwsClient SnsClient client) {
        assertIterableEquals(client.listTopics().topics(), emptyList());
    }
}
// end::user_guide[]
