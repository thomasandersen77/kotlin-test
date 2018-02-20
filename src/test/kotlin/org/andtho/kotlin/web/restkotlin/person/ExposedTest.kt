package org.andtho.kotlin.web.restkotlin.person

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ExposedTest

fun main(args: Array<String>) {
    //an example connection to H2 DB
    Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

    transaction {
        // print sql to std-out
        logger.addLogger(StdOutSqlLogger)

        // insert new city. SQL: INSERT INTO Cities (name) VALUES ('St. Petersburg')
        val stPeteId = Cities.insertAndGetId {
            it[name] = "St. Petersburg"
        }

        // 'select *' SQL: SELECT Cities.id, Cities.name FROM Cities
        println("Cities: ${Cities.selectAll()}")
    }
}

object Cities: IntIdTable() {
    val name = varchar("name", 50)
}