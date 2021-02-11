package com.github.awstestkit.sns.junit5

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Extensions(
    ExtendWith(SnsSetupExtension::class),
    ExtendWith(SnsClientParameterResolver::class),
    ExtendWith(TopicArnParameterResolver::class)
)
annotation class SnsTest
