package org.andtho.kotlin.web.restkotlin.pensjonsberegning.data

import org.andtho.kotlin.web.restkotlin.classesAndInterfaceAndInheritance.data.Author
import org.andtho.kotlin.web.restkotlin.classesAndInterfaceAndInheritance.data.Novel
import org.junit.Test
import java.util.*

class NovelTest {

    @Test
    fun name() {
        val requestImpl = Novel(id = UUID.randomUUID().toString(), content = "this is the content", isbn = "43231")
        println(requestImpl)

        val requestImplSecondConstructor = Novel("", "", author = Author(name = "thomas", age = 40))

        println(requestImplSecondConstructor)
    }
}