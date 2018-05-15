
package org.andtho.kotlin.web.restkotlin

import com.mongodb.annotations.ThreadSafe
import org.andtho.kotlin.web.restkotlin.person.Person
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.mongodb.morphia.Datastore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ThreadSafe
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestKotlinApplicationTests {

	companion object {
		@ClassRule @JvmField
		val mongoServer = MongoServerResource
	}

	//@get:Rule
	//val mongoServer = MongoServerPerTestResource

	@Autowired lateinit var restTemplate: TestRestTemplate
	@Autowired lateinit var datastore : Datastore

	@Test
	fun `get person`() {
		val key = datastore.save(Person(firstname = "test", lastname = "lastname"))

		val responseEntity = restTemplate.getForEntity("/person/${key.id}", Person::class.java)
		assertNotNull(responseEntity)
		assertEquals(200, responseEntity.statusCodeValue)
		val person = responseEntity.body
		assertEquals("test", person.firstname)
		assertEquals("lastname", person.lastname)
	}

	@Test
	fun `get list of people`() {
		datastore.save(Person(firstname = "test1", lastname = "lastname1"))
		datastore.save(Person(firstname = "test2", lastname = "lastname2"))
		datastore.save(Person(firstname = "test3", lastname = "lastname2"))
		datastore.save(Person(firstname = "test4", lastname = "lastname2"))

		val responseEntity = restTemplate.getForEntity("/person", List::class.java)
		assertNotNull(responseEntity)
		assertEquals(200, responseEntity.statusCodeValue)
		responseEntity.body.forEach(action = { it ->
			println(it.toString())
		})

	}



	@Test
	fun `create person by http post`() {
		val name = "thomas2"
		val personUnderTest = Person(firstname = name, lastname = "andersen")

		val responseEntity = restTemplate.postForEntity("/person", personUnderTest, Person::class.java)
		assertNotNull(responseEntity)
		assertEquals(200, responseEntity.statusCodeValue)

		datastore.createQuery(Person::class.java).filter("firstname", name)
				.asIterable()
				.forEach({
					println("${it.firstname} ${it.lastname} ")
				})

	}
}
