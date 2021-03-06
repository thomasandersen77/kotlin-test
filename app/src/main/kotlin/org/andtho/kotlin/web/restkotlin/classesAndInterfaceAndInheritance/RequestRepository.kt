package org.andtho.kotlin.web.restkotlin.classesAndInterfaceAndInheritance

import org.andtho.kotlin.web.restkotlin.classesAndInterfaceAndInheritance.data.Request
import org.bson.types.ObjectId
import org.mongodb.morphia.Datastore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.ws.rs.NotFoundException

@Repository
class RequestRepository @Autowired constructor(val datastore: Datastore) {

    fun store(request : Request) : String {
        return datastore.save(request).id.toString()
    }

    fun findRequest(id: String): Request {
        val query = datastore.find(Request::class.java)
                .field("_id").equal(ObjectId(id))
        return if (query.get() != null) {
                query.get()
            }
            else {
                throw NotFoundException("No request with id=$id")
            }
    }

}