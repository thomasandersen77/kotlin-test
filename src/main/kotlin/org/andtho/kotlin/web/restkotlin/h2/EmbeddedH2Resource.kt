package org.andtho.kotlin.web.restkotlin.h2

import org.h2.jdbcx.JdbcDataSource
import org.junit.rules.ExternalResource
import java.util.*

class EmbeddedH2Resource : ExternalResource{
    override fun before() {
        val resourceAsStream = this::class.java.getResource("/application.properties").readText()
        System.err.println("before")
        println(resourceAsStream)
    }

    override fun after() {

        System.err.println("after")
    }

    constructor() : super()
}