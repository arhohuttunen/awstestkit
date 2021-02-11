package com.github.awstestkit.secretsmanager.junit5

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.util.AnnotationUtils
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import java.lang.reflect.AnnotatedElement

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
        val secrets = findSecrets(annotatedElement)
        secrets.forEach {
            secretsManagerClient.createSecret(it.name, it.value)
        }
    }

    private fun deleteResources(annotatedElement: AnnotatedElement) {
        val secrets = findSecrets(annotatedElement)
        secrets.forEach {
            secretsManagerClient.deleteSecret(it.name)
        }
    }

    private fun findSecrets(annotatedElement: AnnotatedElement): List<Secret> {
        val secret = findSecretsManagerSecret(annotatedElement)
        secret?.apply {
            return listOf(secret)
        }
        return findSecretsManagerSecrets(annotatedElement)
    }

    private fun findSecretsManagerSecret(annotatedElement: AnnotatedElement): Secret? {
        return AnnotationUtils.findAnnotation(annotatedElement, Secret::class.java).orElse(null)
    }

    private fun findSecretsManagerSecrets(annotatedElement: AnnotatedElement): List<Secret> {
        val secrets = AnnotationUtils.findAnnotation(annotatedElement, Secrets::class.java)
        if (secrets.isPresent) {
            return secrets.get().value.toList()
        }
        return emptyList()
    }

}
