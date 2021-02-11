package com.github.awstestkit

import java.net.URI
import java.net.URISyntaxException

interface Endpoint {
    @Throws(URISyntaxException::class)
    fun url(): URI
    fun region(): String
    fun accessKey(): String
    fun secretKey(): String
}
