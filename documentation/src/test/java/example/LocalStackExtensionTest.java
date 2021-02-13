package example;

import com.github.awstestkit.AwsClient;
import com.github.awstestkit.localstack.junit5.LocalStackTest;
import com.github.awstestkit.sns.junit5.SnsTest;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.sns.SnsClient;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

// tag::user_guide[]
@LocalStackTest
@SnsTest
class LocalStackExtensionTest {
    @Test
    void localStackIsRunning(@AwsClient SnsClient client) {
        assertIterableEquals(client.listTopics().topics(), emptyList());
    }
}
// end::user_guide[]
