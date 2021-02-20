package com.github.awstestkit.s3.junit5

import com.github.awstestkit.AwsClient
import com.github.awstestkit.localstack.junit5.LocalStackTest
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import org.junit.jupiter.api.Test
import software.amazon.awssdk.services.s3.S3Client

@LocalStackTest
@S3Test
@S3Bucket("test-bucket")
@S3Objects(
    S3Object("test-bucket", "path/file.txt", "contents")
)
class S3BucketTest {
    @Test
    fun `buckets and objects are created from class annotations`(@AwsClient s3Client: S3Client) {
        val response = s3Client.listBuckets()
        response.buckets().shouldNotBeEmpty()
    }

    @Test
    @S3Bucket("another-bucket")
    @S3Object("another-bucket", "file.txt", "contents")
    fun `buckets and objects are created from method annotations`(@AwsClient s3Client: S3Client) {
        val response = s3Client.listBuckets()
        response.buckets().shouldHaveSize(2)
    }
}
