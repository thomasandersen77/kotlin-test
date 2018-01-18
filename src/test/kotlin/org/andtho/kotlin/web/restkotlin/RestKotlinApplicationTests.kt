
package org.andtho.kotlin.web.restkotlin

import org.junit.Test
import org.junit.runner.RunWith
import org.mongodb.morphia.Datastore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestKotlinApplicationTests {

	@Autowired lateinit var restTemplate: TestRestTemplate
	@Autowired lateinit var datastore : Datastore


	@Test
	fun `get list of person`() {
		val responseEntity = restTemplate.getForEntity("/person/0", List::class.java)
		assertNotNull(responseEntity)
		assertEquals(200, responseEntity.statusCodeValue)
	}

	@Test
	fun `create person by http post`() {
		val name = "thomas2"
		val personUnderTest = Person(firstname = name, lastname = "andersen")

		val responseEntity = restTemplate.postForEntity("/person", personUnderTest, Person::class.java)
		assertNotNull(responseEntity)
		assertEquals(200, responseEntity.statusCodeValue)



		datastore.createQuery(Person::class.java).filter("firstname", name).asIterable()
				.forEach({
					println("${it.firstname} ${it.lastname} ")
				})

	}
}
