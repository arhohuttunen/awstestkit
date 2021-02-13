package example;

// tag::user_guide[]

import com.github.awstestkit.AwsClient;
import com.github.awstestkit.AwsEndpoint;
import com.github.awstestkit.sns.junit5.SnsTest;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.sns.SnsClient;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@SnsTest
@AwsEndpoint(endpointResolver = MyEndpointResolver.class)
class CustomEndpointTest {
    @Test
    void getTopics(@AwsClient SnsClient client) {
        assertIterableEquals(client.listTopics().topics(), emptyList());
    }
}
// end::user_guide[]
