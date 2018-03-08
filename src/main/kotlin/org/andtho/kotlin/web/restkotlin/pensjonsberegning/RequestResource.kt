package org.andtho.kotlin.web.restkotlin.pensjonsberegning

import org.andtho.kotlin.web.restkotlin.pensjonsberegning.data.Request
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.ws.rs.*

@Service
@Path("/request")
class RequestResource @Autowired constructor(val repository: RequestRepository) {

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    fun createRequest(request: Request) : String {
        return repository.store(request)
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    fun findRequest(@PathParam("id") id : String) : Request {
        val findRequest = repository.findRequest(id)
        return findRequest
    }
}