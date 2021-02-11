package com.github.awstestkit.secretsmanager.junit5

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Extensions(
    ExtendWith(SecretsManagerSetupExtension::class),
    ExtendWith(SecretsManagerClientParameterResolver::class)
)
annotation class SecretsManagerTest
