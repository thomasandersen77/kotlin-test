package org.andtho.kotlin.web.restkotlin.h2

import org.junit.rules.ExternalResource

class EmbeddedH2Resource : ExternalResource() {
    override fun before() {
     //   val resourceAsStream = this::class.java.getResource("/application.properties").readText()
        System.err.println("before")
    }

    override fun after() {

        System.err.println("after")
    }

}