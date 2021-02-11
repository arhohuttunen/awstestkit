package com.github.awstestkit.sns.junit5

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class TopicArn(val value: String)
