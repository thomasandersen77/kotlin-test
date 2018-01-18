package org.andtho.kotlin.web.restkotlin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.ws.rs.*

@Component
@Path("person")
class PersonResource @Autowired constructor(
                     val repository: PersonRepository) {

    @GET
    @Path("{_id}")
    @Produces("application/json")
    fun getPerson(@PathParam("_id") id : String) : List<Person> {
        val personList = repository.getPersonById(id) ?: throw NotFoundException("No person with _id = $id")
        return personList.toList()
    }

    @POST
    @Consumes("application/json")
    fun createPerson(person: Person) : Person {
        repository.createPerson(person)
        return person
    }
}