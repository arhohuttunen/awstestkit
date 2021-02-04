package com.github.arhohuttunen

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.util.AnnotationUtils
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import java.lang.reflect.AnnotatedElement
import java.util.Optional

class LocalStackSecretsManagerExtension : BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {
    private lateinit var secretsManagerClient: SimpleSecretsManagerClient

    override fun beforeAll(context: ExtensionContext) {
        secretsManagerClient = SimpleSecretsManagerClient(SecretsManagerClientParameterResolver().createClient(SecretsManagerClient::class, context) as SecretsManagerClient)

        createResources(context.requiredTestClass)
    }

    override fun afterAll(context: ExtensionContext) {
        deleteResources(context.requiredTestClass)
    }

    override fun beforeEach(context: ExtensionContext) {
        createResources(context.requiredTestMethod)
    }

    override fun afterEach(context: ExtensionContext) {
        deleteResources(context.requiredTestMethod)
    }

    private fun createResources(annotatedElement: AnnotatedElement) {
        val annotation = findAnnotation(annotatedElement)
        if (annotation.isPresent) {
            annotation.get().secrets.forEach {
                secretsManagerClient.createSecret(it.name, it.value)
            }
        }
    }

    private fun deleteResources(annotatedElement: AnnotatedElement) {
        val annotation = findAnnotation(annotatedElement)
        if (annotation.isPresent) {
            annotation.get().secrets.forEach {
                secretsManagerClient.deleteSecret(it.name)
            }
        }
    }

    private fun findAnnotation(annotatedElement: AnnotatedElement): Optional<LocalStackSecretsManager> {
        return AnnotationUtils.findAnnotation(annotatedElement, LocalStackSecretsManager::class.java)
    }
}