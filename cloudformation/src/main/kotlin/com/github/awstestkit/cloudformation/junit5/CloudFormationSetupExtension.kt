package com.github.awstestkit.cloudformation.junit5

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.util.AnnotationUtils
import software.amazon.awssdk.services.cloudformation.CloudFormationClient
import java.lang.reflect.AnnotatedElement

class CloudFormationSetupExtension : BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {
    private lateinit var cloudFormationClient: SimpleCloudFormationClient

    override fun beforeAll(context: ExtensionContext) {
        val factory = CloudFormationClientFactory(CloudFormationClient.builder())
        cloudFormationClient = SimpleCloudFormationClient(factory.create(context))

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
        val stacks = findStacks(annotatedElement)
        stacks.forEach {
            cloudFormationClient.createStack(it.name, it.templateFile)
        }
    }

    private fun deleteResources(annotatedElement: AnnotatedElement) {
        val stacks = findStacks(annotatedElement)
        stacks.forEach {
            cloudFormationClient.deleteStack(it.name)
        }
    }

    private fun findStacks(annotatedElement: AnnotatedElement): List<CfnStack> {
        val stack = findCfnStack(annotatedElement)
        stack?.apply {
            return listOf(stack)
        }
        return findCfnStacks(annotatedElement)
    }

    private fun findCfnStack(annotatedElement: AnnotatedElement): CfnStack? {
        return AnnotationUtils.findAnnotation(annotatedElement, CfnStack::class.java).orElse(null)
    }

    private fun findCfnStacks(annotatedElement: AnnotatedElement): List<CfnStack> {
        val stacks = AnnotationUtils.findAnnotation(annotatedElement, CfnStacks::class.java)
        if (stacks.isPresent) {
            return stacks.get().value.toList()
        }
        return emptyList()
    }
}
