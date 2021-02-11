package example;

// tag::user_guide[]
import com.github.awstestkit.AwsClient;
import com.github.awstestkit.cloudformation.junit5.CloudFormationClientParameterResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(CloudFormationClientParameterResolver.class)
class InjectingClient {
    private final CloudFormationClient client;

    InjectingClient(@AwsClient CloudFormationClient client) {
        this.client = client;
    }

    @Test
    void injectAsConstructorParameter() {
        assertNotNull(client);
    }

    @Test
    void injectAsMethodParameter(@AwsClient CloudFormationClient client) {
        assertNotNull(client);
    }
}
// end::user_guide[]
