package org.andtho.kotlin.web.restkotlin.classesAndInterfaceAndInheritance.data

class Novel(var id: String, var content: String, var isbn : String, var author: Author) : Book {

    override fun isbn(): String {
        return isbn;
    }

    override fun id() : String {
        return id
    }

    override fun content(): String {
        return content
    }

    override fun toString(): String {
        return "Novel(id='$id', content='$content', author=$author)"
    }


}