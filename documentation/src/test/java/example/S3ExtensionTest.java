package example;

import com.github.awstestkit.AwsClient;
import com.github.awstestkit.localstack.junit5.LocalStackTest;
import com.github.awstestkit.s3.junit5.S3Bucket;
import com.github.awstestkit.s3.junit5.S3Object;
import com.github.awstestkit.s3.junit5.S3Objects;
import com.github.awstestkit.s3.junit5.S3Test;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.s3.S3Client;

import static org.junit.jupiter.api.Assertions.assertFalse;

// tag::user_guide[]
@LocalStackTest
@S3Test
@S3Bucket("com.github.awstestkit.bucket")
@S3Objects(
        @S3Object(
                bucketName = "com.github.awstestkit.bucket",
                key = "/path/to/file",
                content = "content"
        )
)
class S3ExtensionTest {
    @Test
    void setupBuckets(@AwsClient S3Client client) {
        assertFalse(client.listBuckets().buckets().isEmpty());
    }
}
// end::user_guide[]
