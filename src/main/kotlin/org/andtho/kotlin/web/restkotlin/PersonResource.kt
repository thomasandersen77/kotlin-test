package org.andtho.kotlin.web.restkotlin

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.ws.rs.*

@Component
@Path("person")
class PersonResource @Autowired constructor(
                     val repository: PersonRepository) {
    val log: Logger = LoggerFactory.getLogger(PersonResource::class.java)

    @GET
    @Path("{_id}")
    @Produces("application/json")
    fun getPerson(@PathParam("_id") id : String) : Person {
        val person = repository.getPersonById(id)
        return person ?: throw NotFoundException("No person with _id = $id")
    }

    @GET
    @Produces("application/json")
    fun getPersonList() : List<Person> = repository.getPersonList()

    @POST
    @Consumes("application/json")
    fun createPerson(person: Person) : Person {
        repository.createPerson(person)
        log.debug("Created Person with Id = ${person.id}")
        return person
    }
}