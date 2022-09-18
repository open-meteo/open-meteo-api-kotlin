package com.openmeteo.api.common.query

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.test.Test

class QueryTest {

    data class Pet(
        val name: String,
        val age: Float,
    ) : Query

    @Test
    fun plain() {
        assert(Pet("Bob", 3f)
            .asString() == "?age=3.0&name=Bob")
    }

    interface ProgrammingLanguages {
        @SerialName("languages")
        val programmingLanguages: List<String>
    }

    data class Programmer(
        val name: String,
        val surname: String,
        override val programmingLanguages: List<String>,
    ) : Query, ProgrammingLanguages

    @Test
    fun `with arrays`() {
        val programmer = Programmer("John", "Doe", listOf(
            "Kotlin",
            "Java",
            "JavaScript",
            "HTML",
            "CSS",
        ))
        assert(programmer.asString() == "?name=John&languages=Kotlin,Java,JavaScript,HTML,CSS&surname=Doe")
    }

}
