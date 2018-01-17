package org.andtho.kotlin.web.restkotlin

import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id
import java.time.LocalDate
import java.time.Period
import java.util.*

@Entity
class Person
            constructor(@Id var id : String = "", val firstname : String,
                  val lastname : String,
                  val birthdate : LocalDate = LocalDate.now()
            ) {



    fun alder() : Int = Period.between(birthdate, LocalDate.now()).years


}