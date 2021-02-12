package com.github.awstestkit.cloudformation.junit5

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Extensions(
    ExtendWith(CloudFormationSetupExtension::class),
    ExtendWith(CloudFormationClientParameterResolver::class)
)
annotation class CloudFormationTest
