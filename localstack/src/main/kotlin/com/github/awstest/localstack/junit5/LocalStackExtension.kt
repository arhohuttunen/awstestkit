package com.github.awstest.localstack.junit5

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.utility.DockerImageName

internal const val NAMESPACE = "com.github.arhohuttunen.awsclient-junit5"

class LocalStackExtension : BeforeAllCallback, AfterAllCallback {
    private val namespace = ExtensionContext.Namespace.create(NAMESPACE)

    private lateinit var container: LocalStackContainer

    override fun beforeAll(context: ExtensionContext) {
        val annotation = context.requiredTestClass.getAnnotation(LocalStackTest::class.java)

        @Suppress("SpreadOperator")
        container = LocalStackContainer(DockerImageName.parse("localstack/localstack:0.12.6"))
            .withServices(*annotation.services)
        container.start()

        context.getStore(namespace).put("container", container)
    }

    override fun afterAll(context: ExtensionContext) {
        container.stop()
        context.getStore(namespace).remove("container")
    }
}
