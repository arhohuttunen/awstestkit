package com.github.awstest.sns.junit5

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Extensions(
    ExtendWith(SnsSetupExtension::class),
    ExtendWith(SnsClientParameterResolver::class)
)
annotation class SnsSetup(val topicNames: Array<String> = [])