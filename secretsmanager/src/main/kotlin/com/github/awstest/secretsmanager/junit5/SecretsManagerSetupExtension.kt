package com.github.awstest.secretsmanager.junit5

import com.github.awstest.secretsmanager.SimpleSecretsManagerClient
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.util.AnnotationUtils
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import java.lang.reflect.AnnotatedElement
import java.util.Optional

class SecretsManagerSetupExtension : BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {
    private lateinit var secretsManagerClient: SimpleSecretsManagerClient

    override fun beforeAll(context: ExtensionContext) {
        val factory = SecretsManagerClientFactory(SecretsManagerClient.builder())
        secretsManagerClient = SimpleSecretsManagerClient(factory.create(context))

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

    private fun findAnnotation(annotatedElement: AnnotatedElement): Optional<SecretsManagerSetup> {
        return AnnotationUtils.findAnnotation(annotatedElement, SecretsManagerSetup::class.java)
    }
}
