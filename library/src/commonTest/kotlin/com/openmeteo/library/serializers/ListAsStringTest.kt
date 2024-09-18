package com.openmeteo.library.serializers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.encodeToStringMap
import kotlin.test.Test
import kotlin.test.assertTrue

@Serializable
enum class MyEnum {
    @SerialName("temperature_2m")
    Temperature2m,
    @SerialName("rain")
    Rain,
    @SerialName("snow")
    Snow,
}

@Serializable
data class Person(
    val name: String,
    @SerialName("years")
    val age: Int,
)

@Serializable
data class MyData(
    val enums: ListAsString<MyEnum>,
    val name: String,
    val numbers: ListAsString<Int>,
    val booleans: ListAsString<Boolean>,
    val nullable: String?,
    val person: Person
)

class CommaSeparatedListTest {

    @OptIn(ExperimentalSerializationApi::class)
    @Test
    fun encoding() {

        val data = MyData(
            listOf(
                MyEnum.Temperature2m,
                MyEnum.Snow,
                MyEnum.Rain
            ),
            "Davide",
            listOf(
                4, 8, 1, 7, -3
            ),
            listOf(
                true, false, false, true, true, false
            ),
            null,
            Person("Davide", 19)
        )

        val queryParams = Properties.encodeToStringMap(data)
        assertTrue {
            queryParams == mapOf(
                "enums" to "temperature_2m,snow,rain",
                "name" to "Davide",
                "numbers" to "4,8,1,7,-3",
                "booleans" to "true,false,false,true,true,false",
                "person.name" to "Davide",
                "person.years" to "19"
            )
        }

    }
}