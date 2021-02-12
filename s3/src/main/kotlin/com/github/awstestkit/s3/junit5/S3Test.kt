package com.github.awstestkit.s3.junit5

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Extensions(
    ExtendWith(S3SetupExtension::class),
    ExtendWith(S3ClientParameterResolver::class)
)
annotation class S3Test
