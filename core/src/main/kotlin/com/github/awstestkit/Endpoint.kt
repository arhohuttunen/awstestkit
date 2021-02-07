package com.github.awstestkit

import java.net.URI

interface Endpoint {
    fun url(): URI
    fun region(): String
    fun accessKey(): String
    fun secretKey(): String
}
