package org.andtho.kotlin.web.restkotlin.classesAndInterfaceAndInheritance.data

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class NovelTest {

    @Test
    fun name() {
        val book = Novel(id = UUID.randomUUID().toString(),
                content = "this is the content", isbn = "43231", author = Author(name = "thomas", age = 40))
        assertNotNull(book)
        assertEquals("thomas", book.author.name)
        assertEquals(40, book.author.age)


    }
}