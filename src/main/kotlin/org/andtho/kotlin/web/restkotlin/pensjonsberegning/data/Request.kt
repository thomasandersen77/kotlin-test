package org.andtho.kotlin.web.restkotlin.pensjonsberegning.data

import org.mongodb.morphia.annotations.Id

class Request constructor(@Id var id : String? = null, val requestId : String = "") {
}