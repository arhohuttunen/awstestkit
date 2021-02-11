package com.github.awstestkit.ses.junit5

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Extensions(
    ExtendWith(SesClientParameterResolver::class)
)
annotation class SesTest
