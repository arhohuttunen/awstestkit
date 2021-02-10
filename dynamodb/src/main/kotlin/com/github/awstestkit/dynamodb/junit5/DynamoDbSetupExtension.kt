package com.github.awstestkit.dynamodb.junit5

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.util.AnnotationUtils
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.lang.reflect.AnnotatedElement
import java.util.Optional

class DynamoDbSetupExtension : BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {
    private lateinit var dynamoDbClient: SimpleDynamoDbClient

    override fun beforeAll(context: ExtensionContext) {
        val factory = DynamoDbClientFactory(DynamoDbClient.builder())
        dynamoDbClient = SimpleDynamoDbClient(factory.create(context))

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
            annotation.get().tables.forEach { table ->
                dynamoDbClient.createTable(
                    table.tableName,
                    table.keySchema.associateBy({ it.attributeName }, { it.keyType })
                )
            }
        }
    }

    private fun deleteResources(annotatedElement: AnnotatedElement) {
        val annotation = findAnnotation(annotatedElement)
        if (annotation.isPresent) {
            annotation.get().tables.forEach { table ->
                dynamoDbClient.deleteTable(table.tableName)
            }
        }
    }

    private fun findAnnotation(annotatedElement: AnnotatedElement): Optional<DynamoDbSetup> {
        return AnnotationUtils.findAnnotation(annotatedElement, DynamoDbSetup::class.java)
    }
}
