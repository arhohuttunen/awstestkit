package com.github.arhohuttunen

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Extensions(
    ExtendWith(LocalStackSqsExtension::class),
    ExtendWith(SqsClientParameterResolver::class)
)
annotation class LocalStackSqs(val queueNames: Array<String> = [])