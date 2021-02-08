package example

// tag::user_guide[]
import com.github.awstestkit.AwsClient
import com.github.awstestkit.cloudformation.junit5.CloudFormationClientParameterResolver
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.amazon.awssdk.services.cloudformation.CloudFormationClient

@ExtendWith(CloudFormationClientParameterResolver::class)
class InjectingClient(@AwsClient private val client: CloudFormationClient) {
    @Test
    fun `inject as constructor parameter`() {
        assertNotNull(client)
    }

    @Test
    fun `inject as method parameter`(@AwsClient client: CloudFormationClient) {
        assertNotNull(client)
    }
}
// end::user_guide[]
