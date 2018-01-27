package org.andtho.kotlin.web.restkotlin.h2

import org.h2.jdbcx.JdbcDataSource
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.env.AbstractEnvironment
import org.springframework.core.env.EnumerablePropertySource
import org.springframework.core.env.Environment
import org.springframework.core.env.MutablePropertySources
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
import java.util.function.Consumer
import java.util.stream.StreamSupport

@RunWith(SpringRunner::class)
class H2Test {

    @Autowired lateinit var env : Environment

    @Test
    fun `validate spring envionment`() {
        assertNotNull(env)
        val propSrcs = env as AbstractEnvironment
        val map = propSrcs.propertySources.filter { it -> it is EnumerablePropertySource }
                .map { it -> it as EnumerablePropertySource }
                .map { it -> it.propertyNames }
                .map { it -> it.iterator() }
                .forEach(action = {
                    if (it.hasNext())
                        println(it.next())
                })

//
//                .filter(ps -> ps instanceof EnumerablePropertySource)
//        .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
//        .flatMap(Arrays::<String>stream)
    }

    @Test
    fun `test start h2 in-memory`() {
        val dataSource = JdbcDataSource()
        val mongo = env.getProperty("mongo.host")
        val user = env.getProperty("db.user")
        val password = env.getProperty("db.password")

        dataSource.user = user
        dataSource.password = password
        dataSource.setURL(env.getProperty("db.url"))
        val connection = dataSource.connection
        assertNotNull(connection)
    }
}