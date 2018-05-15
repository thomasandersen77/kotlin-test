
package org.andtho.kotlin.web.restkotlin.person

import org.bson.types.ObjectId
import org.junit.Test
import java.time.LocalDate
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal class PersonTest {

    @Test
    fun test_alder() {
        val thomas = Person(ObjectId.get().toString(), "", "", LocalDate.of(1977, 9, 7))
        assertEquals(40, thomas.alder())
    }

    @Test
    internal fun test_constructor() {
        val person = Person(firstname = "firstname", lastname = "lastname", id = ObjectId.get().toString())
        assertNotNull(person)
    }

}

fun generate() : String = UUID.randomUUID().toString()