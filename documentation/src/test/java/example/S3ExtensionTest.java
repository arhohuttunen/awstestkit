package example;

import com.github.awstestkit.AwsClient;
import com.github.awstestkit.localstack.junit5.LocalStackTest;
import com.github.awstestkit.s3.junit5.S3Object;
import com.github.awstestkit.s3.junit5.S3Setup;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.localstack.LocalStackContainer;
import software.amazon.awssdk.services.s3.S3Client;

import static org.junit.jupiter.api.Assertions.assertFalse;

// tag::user_guide[]
@LocalStackTest(services = LocalStackContainer.Service.S3)
@S3Setup(
        bucketNames = "com.github.awstestkit.bucket",
        objects = {
                @S3Object(
                        bucketName = "com.github.awstestkit.bucket",
                        key = "/path/to/file",
                        content = "content"
                )
        }
)
class S3ExtensionTest {
    @Test
    void setupBuckets(@AwsClient S3Client client) {
        assertFalse(client.listBuckets().buckets().isEmpty());
    }
}
// end::user_guide[]
