package com.openmeteo.sdk

import com.google.flatbuffers.kotlin.ArrayReadWriteBuffer
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class PersonTest {

    @Test
    fun testExample() = runBlocking {
        val client = HttpClient()
        val res = client.get("http://localhost:8080/person.bin")
        val body = res.body() as ByteArray
        val person = Person.asRoot(ArrayReadWriteBuffer(body))
        println("Name: ${person.name}")
        println("Age: ${person.age}")
    }
}
