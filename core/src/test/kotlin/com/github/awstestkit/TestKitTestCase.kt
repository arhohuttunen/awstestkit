package com.github.awstestkit

import org.junit.jupiter.api.extension.ConditionEvaluationResult
import org.junit.jupiter.api.extension.ExecutionCondition
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.engine.discovery.DiscoverySelectors.selectMethod
import org.junit.platform.testkit.engine.EngineExecutionResults
import org.junit.platform.testkit.engine.EngineTestKit
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.jvm.javaMethod

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ExtendWith(
    EnableInTestKit::class
)
annotation class TestKitTestCase

fun runTest(testClass: KClass<*>, testMethod: KFunction<*>): EngineExecutionResults {
    return EngineTestKit
        .engine("junit-jupiter")
        .configurationParameter("testkit.testcase.enabled", "true")
        .selectors(selectMethod(testClass.java, testMethod.javaMethod))
        .execute()
}

internal class EnableInTestKit : ExecutionCondition {
    override fun evaluateExecutionCondition(context: ExtensionContext): ConditionEvaluationResult {
        return if (context.getConfigurationParameter("testkit.testcase.enabled").isPresent) {
            ConditionEvaluationResult.enabled(
                "Enabled when using from EngineTestKit"
            )
        } else {
            ConditionEvaluationResult.disabled(
                "Tests annotated with @TestKitTestCase are only run as part of other tests"
            )
        }
    }
}