package com.openmeteo.api.common.query

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals

class QueryTest {

    @Serializable
    class Pet(
        val name: String,
        val age: Float,
    ) : Query

    @Test
    fun plain() {
        val pet = Pet("Bob", 3f)
        assertEquals("?name=Bob&age=3.0", Query.asString(pet))
    }

    @Serializable
    class Programmer(
        val name: String,
        val surname: String,
        @Serializable(with = ListAsString::class)
        @SerialName("languages")
        val programmingLanguages: List<String>,
    ) : Query

    @Test
    fun lists() {
        val programmer = Programmer("John", "Doe", listOf(
            "Kotlin",
            "Java",
            "JavaScript",
            "HTML",
            "CSS",
        ))
        // Do not worry, %2C is the URL encoded comma: it gets decoded properly before splitting
        //assertEquals("?name=John&languages=Kotlin,Java,JavaScript,HTML,CSS&surname=Doe",
        assertEquals("?name=John&surname=Doe&languages=Kotlin%2CJava%2CJavaScript%2CHTML%2CCSS",
            Query.asString(programmer))
    }
    @Serializable
    class Timezone(
        override val timezone: com.openmeteo.api.common.time.Timezone?
    ) : Query.Timezone

    @Test
    fun timezone_auto() {
        val timezone = Timezone(com.openmeteo.api.common.time.Timezone.auto)
        assertEquals("?timezone=auto", Query.asString(timezone))
    }

}
