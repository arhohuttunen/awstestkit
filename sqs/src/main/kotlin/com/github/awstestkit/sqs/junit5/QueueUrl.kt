package com.github.awstestkit.sqs.junit5

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class QueueUrl(val value: String)
