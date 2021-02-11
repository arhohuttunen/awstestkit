package example;

import com.github.awstestkit.AwsClient;
import com.github.awstestkit.cloudformation.junit5.CfnStack;
import com.github.awstestkit.cloudformation.junit5.CloudFormationSetup;
import com.github.awstestkit.localstack.junit5.LocalStackTest;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.localstack.LocalStackContainer;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;

import static org.junit.jupiter.api.Assertions.assertFalse;

// tag::user_guide[]
@LocalStackTest(services = LocalStackContainer.Service.CLOUDFORMATION)
@CloudFormationSetup(stacks = {
        @CfnStack(stackName = "awstestkit-stack", templateFile = "src/test/resources/template.yml")
})
class CloudFormationExtensionTest {
    @Test
    void setupStacks(@AwsClient CloudFormationClient client) {
        assertFalse(client.listStacks().stackSummaries().isEmpty());
    }
}
// end::user_guide[]
