package com.github.arhohuttunen

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Secret(
    val name: String,
    val value: String
)

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Extensions(
    ExtendWith(LocalStackSecretsManagerExtension::class),
    ExtendWith(SecretsManagerClientParameterResolver::class)
)
annotation class LocalStackSecretsManager(val secrets: Array<Secret> = [])