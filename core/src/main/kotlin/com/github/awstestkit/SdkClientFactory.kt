package com.github.awstestkit

import org.junit.jupiter.api.extension.ExtensionContext
import software.amazon.awssdk.core.SdkClient
import kotlin.reflect.KClass

interface SdkClientFactory {
    fun isSupported(type: KClass<out Any>): Boolean
    fun create(type: KClass<out Any>, extensionContext: ExtensionContext): SdkClient
}
