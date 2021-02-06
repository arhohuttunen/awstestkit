package com.github.arhohuttunen

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Stack(val stackName: String, val templateFile: String)

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Extensions(
    ExtendWith(LocalStackCloudFormationExtension::class),
    ExtendWith(CloudFormationClientParameterResolver::class)
)
annotation class LocalStackCloudFormation(vararg val stacks: Stack)