package org.andtho.kotlin.web.restkotlin

import org.mongodb.morphia.Datastore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class PersonRepository @Autowired constructor(val datastore: Datastore) {

    fun getPersonById(id: String) : MutableList<Person>? {
        val createQuery = datastore.createQuery(Person::class.java)
        return createQuery.field("_id").equal(id).asList()
    }

    fun createPerson(person: Person) : Person {
        val key = datastore.save(person)
        println("Created a person. Key = ${key}")
        return person
    }

}