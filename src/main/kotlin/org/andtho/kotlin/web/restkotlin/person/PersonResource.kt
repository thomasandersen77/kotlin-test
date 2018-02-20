package org.andtho.kotlin.web.restkotlin.person

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
    fun getPerson(@PathParam("_id") id : String) : Person = repository.getPersonById(id)!!

    @GET
    @Produces("application/json")
    fun getPersonList() : List<Person> = repository.getPersonList()

    @POST
    @Consumes("application/json")
    fun createPerson(person: Person) : Person = repository.createPerson(person)

}