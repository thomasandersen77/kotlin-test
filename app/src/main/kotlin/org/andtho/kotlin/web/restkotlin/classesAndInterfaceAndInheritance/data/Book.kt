package org.andtho.kotlin.web.restkotlin.classesAndInterfaceAndInheritance.data

import java.util.*

interface Book {
    fun id() : UUID
    fun content() : String
    fun isbn() : String
}