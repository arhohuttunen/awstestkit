package com.github.awstestkit.dynamodb.junit5

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

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class DynamoDbTable(
    val tableName: String,
    val keySchema: Array<DynamoDbKeySchemaElement> = [],
    val attributeDefinitions: Array<DynamoDbAttributeDefinition> = []
)

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class DynamoDbTables(vararg val value: DynamoDbTable)
