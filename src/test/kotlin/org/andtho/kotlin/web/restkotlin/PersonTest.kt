
package org.andtho.kotlin.web.restkotlin

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import java.time.LocalDate
import java.util.*

internal class PersonTest {

    @Test
    fun test_alder() {
        val thomas = Person("", "", "", LocalDate.of(1977, 9, 7))
        assertEquals(40, thomas.alder())
    }

    @Test
    internal fun test_constructor() {
        val person = Person(firstname = "firstname", lastname = "lastname")
        assertNotNull(person)
    }

}

fun generate() : String = UUID.randomUUID().toString()