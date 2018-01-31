package org.andtho.kotlin.web.restkotlin.h2

import org.h2.jdbcx.JdbcDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.sql.Connection

@Component
class H2Connection @Autowired constructor(val environment: Environment) {
    fun connection() : Connection {
        val dataSource = JdbcDataSource()
        val user = environment.getProperty("db.user")
        val password = environment.getProperty("db.password")
        val url = environment.getProperty("db.url")

        dataSource.user = user
        dataSource.password = password
        dataSource.setURL(url)

        return dataSource.connection
    }
}