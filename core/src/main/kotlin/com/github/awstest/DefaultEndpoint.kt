package com.github.awstest

import java.net.URI

class DefaultEndpoint : Endpoint {
    override fun url(): URI = URI("https://localhost")
    override fun region(): String = "us-east-1"
    override fun accessKey(): String = "accessKey"
    override fun secretKey(): String = "secretKey"
}