package org.andtho.kotlin.web.restkotlin

import org.bson.types.ObjectId
import org.mongodb.morphia.Datastore
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class PersonRepository @Autowired constructor(val datastore: Datastore) {
    val log : Logger = LoggerFactory.getLogger(PersonRepository::class.java)

    fun getPersonById(id: String) : Person? {
        val query = datastore.createQuery(Person::class.java)
        val person = query.field("_id").equal(ObjectId(id)).get()
        log.info("Get person with id = ${person.id}")
        return person
    }

    fun getPersonList() : List<Person> {
        val listOfPeople = datastore.createQuery(Person::class.java).asList()
        log.info("get list of people = ${listOfPeople.size}")
        return listOfPeople
    }

    fun createPerson(person: Person) : Person {
        val key = datastore.save(person)
        log.info("Created person. Key = ${key.id}")
        return person
    }

}