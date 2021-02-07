package com.github.awstest.s3.junit5

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class S3Object(
    val bucketName: String,
    val key: String,
    val content: String
)

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Extensions(
    ExtendWith(S3SetupExtension::class),
    ExtendWith(S3ClientParameterResolver::class)
)
annotation class S3Setup(val bucketNames: Array<String> = [], val objects: Array<S3Object> = [])
