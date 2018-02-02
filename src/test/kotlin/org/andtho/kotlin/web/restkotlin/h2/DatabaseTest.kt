package org.andtho.kotlin.web.restkotlin.h2

import org.h2.jdbcx.JdbcDataSource
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Ignore
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
import kotlin.test.assertFalse
import kotlin.test.todo

@RunWith(SpringRunner::class)
@SpringBootTest
class DatabaseTest {
    @Autowired lateinit var environment: Environment
    @Autowired lateinit var context : ApplicationContext

    @get:Rule
    val embeddedH2Resource : EmbeddedH2Resource = EmbeddedH2Resource()

    @Before
    fun setUp() {
        assertNotNull(context)
        val db = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver", user = "sa", password = "sa")
        assertNotNull(db.connector.invoke())
    }

    object Person : Table(){
        val id : Column<Int> = integer("id").autoIncrement().primaryKey()
        val firstname : Column<String> = varchar("firstname", 50)
        val lastname : Column<String> = varchar("lastname", 50)
    }

    @Test
    fun `create exposed transaction`() {
        createTablePerson()
        Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver", user = "sa", password = "sa")
        transaction {
            logger.addLogger(StdOutSqlLogger)

            val personId = Person.insert {
                it[Person.firstname] = "firstname"
                it[Person.lastname] = "lastname"
            }
            val query : Query = Person.select { Person.firstname eq "firstname"}
            query.filterNotNull()
                    .iterator()
                    .forEach { println(it) }

            //println("person = ${}")
        }

    }

    private fun createTablePerson() {
        val h2Connection = context.getBean(H2Connection::class.java)
        assertNotNull(h2Connection)

        val connection = h2Connection.connection()
        val createStatement = connection.createStatement()
        val execute = createStatement.execute("create table person (id int, firstname varchar(30), lastname varchar(30))")
        assertFalse { execute }
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

    @Ignore
    @Test
    fun `test h2 connection`() {
        val h2Connection = context.getBean(H2Connection::class.java)
        assertNotNull(h2Connection)

        val connection = h2Connection.connection()
        val createStatement = connection.createStatement()
        val execute = createStatement.execute("create table person (firstname varchar(30), lastname varchar(30))")
        assertFalse { execute }
        val prepareStatement = connection.prepareStatement("insert into person (firstname, lastname) values (?, ?)")
        prepareStatement.setString(1, "test-firstname")
        prepareStatement.setString(2, "test-lastname")
        val updated = prepareStatement.execute()
        assertFalse { updated }
        val res = connection.createStatement().executeQuery("select * from person")
        while (res.next()) {
            println("${res.getString(1)}, ${res.getString(2)}")
        }
    }
}