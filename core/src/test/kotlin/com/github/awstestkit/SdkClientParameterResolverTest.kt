package com.github.awstestkit

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ParameterResolutionException
import org.junit.platform.testkit.engine.EventConditions.event
import org.junit.platform.testkit.engine.EventConditions.finishedSuccessfully
import org.junit.platform.testkit.engine.EventConditions.finishedWithFailure
import org.junit.platform.testkit.engine.EventConditions.test
import org.junit.platform.testkit.engine.TestExecutionResultConditions.instanceOf

class SdkClientParameterResolverTest {

    @Test
    fun `resolve client parameter successfully`() {
        runTest(
            SdkClientParameterResolverTestCase::class,
            SdkClientParameterResolverTestCase::resolveClientParameterSuccessfully
        )
            .testEvents()
            .assertThatEvents().haveExactly(
                1,
                event(
                    test("resolveClientParameterSuccessfully"),
                    finishedSuccessfully()
                )
            )
    }

    @Test
    fun `fail when client not annotated`() {
        runTest(
            SdkClientParameterResolverTestCase::class,
            SdkClientParameterResolverTestCase::failWhenClientNotAnnotated
        )
            .testEvents()
            .assertThatEvents().haveExactly(
                1,
                event(
                    test("failWhenClientNotAnnotated"),
                    finishedWithFailure(
                        instanceOf(ParameterResolutionException::class.java)
                    )
                )
            )
    }

    @Test
    fun `fail with unsupported clients`() {
        runTest(
            SdkClientParameterResolverTestCase::class,
            SdkClientParameterResolverTestCase::failWithUnsupportedClients
        )
            .testEvents()
            .assertThatEvents().haveExactly(
                1,
                event(
                    test("failWithUnsupportedClients"),
                    finishedWithFailure(
                        instanceOf(ParameterResolutionException::class.java)
                    )
                )
            )
    }
}