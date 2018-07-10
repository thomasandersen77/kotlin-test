package org.andtho.kotlin.web.restkotlin.classesAndInterfaceAndInheritance.data

import org.mongodb.morphia.annotations.Id

data class Request(@Id var id : String? = null, val requestId : String = "") 