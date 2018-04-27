package org.andtho.kotlin.web.restkotlin.pensjonsberegning.data

class RequestImpl(var id: String, var content: String) : RequestAdapter<String> {

    override fun id() : String {
        return id
    }

    override fun content(): String {
        return content
    }
}