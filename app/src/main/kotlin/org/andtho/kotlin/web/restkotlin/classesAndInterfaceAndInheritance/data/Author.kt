package org.andtho.kotlin.web.restkotlin.classesAndInterfaceAndInheritance.data

class Author(var name : String, var age : Int) {

    override fun toString(): String {
        return "Author(name='$name', age=$age)"
    }
}
