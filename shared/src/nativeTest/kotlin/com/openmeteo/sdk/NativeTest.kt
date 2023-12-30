package com.openmeteo.sdk

import kotlin.test.Test
import kotlin.test.assertTrue

class NativeTest {

    @Test
    fun testExample() {
        assertTrue(Greeting().greeting().contains("Hello"), "Check 'Hello' is mentioned")
    }
}