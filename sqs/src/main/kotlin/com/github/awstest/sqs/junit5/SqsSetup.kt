package com.github.awstest.sqs.junit5

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Extensions(
    ExtendWith(SqsSetupExtension::class),
    ExtendWith(SqsClientParameterResolver::class)
)
annotation class SqsSetup(val queueNames: Array<String> = [])
