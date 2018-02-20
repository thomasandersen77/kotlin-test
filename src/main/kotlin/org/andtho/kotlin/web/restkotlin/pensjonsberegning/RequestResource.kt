package org.andtho.kotlin.web.restkotlin.pensjonsberegning

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path

@Service
@Path("/request")
class RequestResource @Autowired constructor(val repository: RequestRepository) {

    @POST
    @Consumes("application/json")
    fun createRequest() {

    }
}