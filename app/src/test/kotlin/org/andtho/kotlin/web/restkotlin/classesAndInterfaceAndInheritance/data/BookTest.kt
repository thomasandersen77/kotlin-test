package org.andtho.kotlin.web.restkotlin.classesAndInterfaceAndInheritance.data

import org.junit.Assert.*
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class BookTest {

    @Test
    fun `test interface and inheritance`() {
        val novel = Novel(id = UUID.randomUUID().toString(),
                content = "this is the content", isbn = "43231", author = Author(name = "thomas", age = 40))
        assertNotNull(novel)
        assertEquals("thomas", novel.author.name)
        assertEquals(40, novel.author.age)

        val story = ShortStory(id = UUID.randomUUID(), content = "this is the content", isbn = "432333" )

        val listOf = listOf(story, novel, Author("", 40))
        listOf.listIterator().forEach { it ->
            if(it is Book) {
                assertTrue(it is Book)
                println("Found a Book Type: $it")
            } else {
                println("Type was NOT a Book. Type ${it.javaClass}")
            }

        }

    }
}