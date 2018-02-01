package org.andtho.kotlin.web.restkotlin.h2

import org.h2.jdbcx.JdbcDataSource
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.core.env.AbstractEnvironment
import org.springframework.core.env.EnumerablePropertySource
import org.springframework.core.env.Environment
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(SpringRunner::class)
@SpringBootTest
class H2Test {
    init {
        System.setProperty("db.user", "user")
    }

    @Autowired lateinit var environment: Environment
    @Autowired lateinit var context : ApplicationContext

    @get:Rule
    val embeddedH2Resource : EmbeddedH2Resource = EmbeddedH2Resource()

    @Before
    fun setUp() {
        assertNotNull(context)
        assertEquals("user", environment.getProperty("db.user"))
    }

    @Test
    fun `validate spring properties`() {
        assertNotNull(environment)
        val propSrcs = environment as AbstractEnvironment
        propSrcs.propertySources.filter { it -> it is EnumerablePropertySource }
                .map { it -> it as EnumerablePropertySource }
                .map { it -> it.propertyNames }
                .map { it -> it.iterator() }
                .forEach(action = {
                    if (it.hasNext())
                        println(it.next())
                })
        println("\n")
        val resourceAsStream = this::class.java.getResource("/application.properties").readText()
        println(resourceAsStream)

    }

    @Test
    fun `test start h2 in-memory`() {
        val dataSource = JdbcDataSource()
        val user = environment.getProperty("db.user")
        val password = environment.getProperty("db.password")
        val url = environment.getProperty("db.url")

        dataSource.user = user
        dataSource.password = password
        dataSource.setURL(url)
        val connection = dataSource.connection
        assertNotNull(connection)
    }

    @Test
    fun `test h2 connection`() {
        val h2Connection = context.getBean(H2Connection::class.java)
        assertNotNull(h2Connection)

        val connection = h2Connection.connection()
        val createStatement = connection.createStatement()
        val execute = createStatement.execute("create table person (_id varchar(30))")
        assertFalse { execute }
        val prepareStatement = connection.prepareStatement("insert into person (_id) values (?)")
        prepareStatement.setString(1, "test")
        val updated = prepareStatement.execute()
        assertFalse { updated }
        val resultSet = connection.createStatement().executeQuery("select * from person")
        while (resultSet.next()) {
            val string = resultSet.getString(1)
            println(string)
        }
    }
}