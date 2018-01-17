
package org.andtho.kotlin.web.restkotlin

import org.junit.Test
import org.junit.runner.RunWith
import org.mongodb.morphia.Datastore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Criteria.*
import org.springframework.data.mongodb.core.query.Query
import org.springframework.test.context.junit4.SpringRunner
import java.util.function.Consumer
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestKotlinApplicationTests {

	@Autowired lateinit var restTemplate: TestRestTemplate
	@Autowired lateinit var datastore : Datastore

	@Test
	fun test_morphia_connection() {
		assertNotNull(datastore)
	}

	@Test
	fun getPersonById() {
		assertNotNull(restTemplate)
		val responseEntity = restTemplate.getForEntity("/person/0", List::class.java)
		assertNotNull(responseEntity)
		assertEquals(200, responseEntity.statusCodeValue)
	}

	@Test
	fun createPerson() {
		val name = "thomas2"
		val personUnderTest = Person(firstname = name, lastname = "andersen")

		val responseEntity = restTemplate.postForEntity("/person", personUnderTest, Person::class.java)
		assertNotNull(responseEntity)
		assertEquals(200, responseEntity.statusCodeValue)



		datastore.createQuery(Person::class.java).filter("firstname", name).asIterable()
				.forEach({
					println("${it.firstname} ${it.lastname} ")
				})

		/*
		val query = Query()
				.addCriteria(
				where("firstname").`is`(name))
		val users = mongoTemplate.find(query, Person::class.java)

		users.forEach(Consumer { println(it.id +" " + it.firstname + " " + it.lastname) })

		assertEquals(name,  users.first().firstname)
		*/
	}
}
