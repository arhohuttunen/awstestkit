package com.github.awstest.s3.junit5

import com.github.awstest.AwsClient
import com.github.awstest.localstack.junit5.LocalStackTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import software.amazon.awssdk.services.s3.S3Client

@LocalStackTest(LocalStackContainer.Service.S3)
@S3Setup(bucketNames = ["test-bucket"], objects = [S3Object("test-bucket", "path/file.txt", "contents")])
class S3SetupTest {
    @Test
    fun `buckets and objects are created from class annotations`(@AwsClient s3Client: S3Client) {
        val response = s3Client.listBuckets()
        assertThat(response.buckets()).isNotEmpty()
    }

    @Test
    @S3Setup(bucketNames = ["another-bucket"], objects = [S3Object("another-bucket", "file.txt", "contents")])
    fun `buckets and objects are created from method annotations`(@AwsClient s3Client: S3Client) {
        val response = s3Client.listBuckets()
        assertThat(response.buckets()).hasSize(2)
    }
}
