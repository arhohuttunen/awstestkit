package com.github.awstestkit.sqs.junit5

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Extensions(
    ExtendWith(SqsSetupExtension::class),
    ExtendWith(SqsClientParameterResolver::class)
)
annotation class SqsTest
