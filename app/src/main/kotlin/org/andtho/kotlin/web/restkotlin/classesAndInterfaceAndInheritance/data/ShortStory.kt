package org.andtho.kotlin.web.restkotlin.classesAndInterfaceAndInheritance.data

import java.util.*

class ShortStory(var id: UUID, var isbn: String, var content: String) : Book {

    override fun content(): String {
        return ""
    }

    override fun isbn(): String {
        return ""
    }

    override fun id(): UUID {
        return UUID.randomUUID()
    }

    override fun toString(): String {
        return "ShortStory(id=$id, isbn=$isbn, content='$content')"
    }


}