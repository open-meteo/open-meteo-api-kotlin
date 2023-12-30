package com.openmeteo.sdk

expect val platform: String

class Greeting {
    fun greeting() = "Hello, $platform!"
}