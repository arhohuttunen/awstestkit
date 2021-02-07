package com.github.awstest.s3

import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.CreateBucketRequest
import software.amazon.awssdk.services.s3.model.DeleteBucketRequest
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest

class SimpleS3Client(private val s3Client: S3Client) {
    fun createBucket(bucketName: String) {
        val request = CreateBucketRequest.builder()
            .bucket(bucketName)
            .build()
        s3Client.createBucket(request)
    }

    fun putObject(bucketName: String, key: String, content: String) {
        val request = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build()
        s3Client.putObject(request, RequestBody.fromBytes(content.toByteArray()))
    }

    fun deleteBucket(bucketName: String) {
        val request = DeleteBucketRequest.builder()
            .bucket(bucketName)
            .build()
        s3Client.deleteBucket(request)
    }

    fun deleteObject(bucketName: String, key: String) {
        val request = DeleteObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build()
        s3Client.deleteObject(request)
    }
}
