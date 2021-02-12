package com.github.awstestkit.s3.junit5

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.util.AnnotationUtils
import software.amazon.awssdk.services.s3.S3Client
import java.lang.reflect.AnnotatedElement

class S3SetupExtension : BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {
    private lateinit var s3Client: SimpleS3Client

    override fun beforeAll(context: ExtensionContext) {
        val factory = S3ClientFactory(S3Client.builder())
        s3Client = SimpleS3Client(factory.create(context))

        createResources(context.requiredTestClass)
    }

    override fun afterAll(context: ExtensionContext) {
        deleteResources(context.requiredTestClass)
    }

    override fun beforeEach(context: ExtensionContext) {
        createResources(context.requiredTestMethod)
    }

    override fun afterEach(context: ExtensionContext) {
        deleteResources(context.requiredTestMethod)
    }

    private fun createResources(annotatedElement: AnnotatedElement) {
        val buckets = findBuckets(annotatedElement)
        buckets.forEach {
            s3Client.createBucket(it)
        }
        val objects = findObjects(annotatedElement)
        objects.forEach {
            s3Client.putObject(it.bucketName, it.key, it.content)
        }
    }

    private fun deleteResources(annotatedElement: AnnotatedElement) {
        val objects = findObjects(annotatedElement)
        objects.forEach {
            s3Client.deleteObject(it.bucketName, it.key)
        }
        val buckets = findBuckets(annotatedElement)
        buckets.forEach {
            s3Client.deleteBucket(it)
        }
    }

    private fun findBuckets(annotatedElement: AnnotatedElement): List<String> {
        return AnnotationUtils.findAnnotation(annotatedElement, S3Bucket::class.java)
            .map { it.value.toList() }
            .orElseGet { emptyList() }
    }

    private fun findObjects(annotatedElement: AnnotatedElement): List<S3Object> {
        val obj = findDynamoDbTable(annotatedElement)
        obj?.apply {
            return listOf(obj)
        }
        return findDynamoDbTables(annotatedElement)
    }

    private fun findDynamoDbTable(annotatedElement: AnnotatedElement): S3Object? {
        return AnnotationUtils.findAnnotation(annotatedElement, S3Object::class.java).orElse(null)
    }

    private fun findDynamoDbTables(annotatedElement: AnnotatedElement): List<S3Object> {
        val objects = AnnotationUtils.findAnnotation(annotatedElement, S3Objects::class.java)
        if (objects.isPresent) {
            return objects.get().value.toList()
        }
        return emptyList()
    }
}
