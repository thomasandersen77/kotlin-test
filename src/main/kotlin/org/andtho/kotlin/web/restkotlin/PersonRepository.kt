package org.andtho.kotlin.web.restkotlin

import org.mongodb.morphia.Datastore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class PersonRepository @Autowired constructor(val template: MongoTemplate, val datastore: Datastore) {

    fun getPersonById(id: Int) : MutableList<Person>? {
        Objects.requireNonNull(template, "MongoTemplate can not be null")
        val query = Query()
        query.addCriteria(Criteria.where("firstname").`is`("thomas"))
        val createQuery = datastore.createQuery(Person::class.java)
        return createQuery.field("firstname").equal("firstname").asList()

        //return template.find(query, Person::class.java)
    }

    fun createPerson(person: Person) : Person {
        val key = datastore.save(person)
        println("Created a person. Key is = ${key}")
        return person
    }

}