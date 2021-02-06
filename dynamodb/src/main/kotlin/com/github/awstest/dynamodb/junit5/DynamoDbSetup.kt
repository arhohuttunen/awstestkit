package com.github.awstest.dynamodb.junit5

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import software.amazon.awssdk.services.dynamodb.model.KeyType

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DynamoDbTableKey(
    val attributeName: String,
    val keyType: KeyType = KeyType.HASH
)

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DynamoDbTable(
    val tableName: String,
    val keySchema: Array<DynamoDbTableKey> = []
)

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Extensions(
    ExtendWith(DynamoDbSetupExtension::class),
    ExtendWith(DynamoDbClientParameterResolver::class)
)
annotation class DynamoDbSetup(val tables: Array<DynamoDbTable> = [])