package com.github.arhohuttunen

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import org.testcontainers.containers.localstack.LocalStackContainer

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Extensions(ExtendWith(LocalStackExtension::class))
@AwsEndpoint(LocalStackEndpointResolver::class)
annotation class LocalStackTest(val services: Array<LocalStackContainer.Service> = [])
