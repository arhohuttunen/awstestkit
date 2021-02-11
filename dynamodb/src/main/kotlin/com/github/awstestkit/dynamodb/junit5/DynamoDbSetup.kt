package com.github.awstestkit.dynamodb.junit5

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import software.amazon.awssdk.services.dynamodb.model.KeyType
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DynamoDbKeySchemaElement(
    val attributeName: String,
    val keyType: KeyType = KeyType.HASH
)

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DynamoDbAttributeDefinition(
    val attributeName: String,
    val attributeType: ScalarAttributeType = ScalarAttributeType.S
)

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DynamoDbTable(
    val tableName: String,
    val keySchema: Array<DynamoDbKeySchemaElement> = [],
    val attributeDefinitions: Array<DynamoDbAttributeDefinition> = []
)

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Extensions(
    ExtendWith(DynamoDbSetupExtension::class),
    ExtendWith(DynamoDbClientParameterResolver::class)
)
annotation class DynamoDbSetup(val tables: Array<DynamoDbTable> = [])
