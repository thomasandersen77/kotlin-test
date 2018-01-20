
package org.andtho.kotlin.web.restkotlin

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.runner.RunWith
import org.mongodb.morphia.Datastore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import com.mongodb.MongoClient
import de.flapdoodle.embed.process.runtime.Network
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder
import de.flapdoodle.embed.mongo.MongodProcess
import de.flapdoodle.embed.mongo.MongodExecutable
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.Net
import de.flapdoodle.embed.mongo.distribution.Version
import org.junit.*


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestKotlinApplicationTests {

	@Autowired lateinit var restTemplate: TestRestTemplate
	@Autowired lateinit var datastore : Datastore

	private val starter = MongodStarter.getDefaultInstance()

	private var _mongodExe: MongodExecutable? = null
	private var _mongod: MongodProcess? = null

	private var _mongo: MongoClient? = null

	@Before
	fun `start mongo db`() {
		val port = 27017
		val _mongodExe = starter.prepare(MongodConfigBuilder()
				.version(Version.Main.DEVELOPMENT)
				.net(Net("localhost", port, Network.localhostIsIPv6()))
				.build())
		_mongod = _mongodExe.start()
		_mongo = MongoClient("localhost", port)
	}

	@After
	fun tearDown() {
		_mongod?.stop()
	}

	@Test
	fun `get person`() {
		val responseEntity = restTemplate.getForEntity("/person/5a635df6dbc6900bdefd6139", Person::class.java)
		assertNotNull(responseEntity)
		assertEquals(200, responseEntity.statusCodeValue)
		val person = responseEntity.body
		assertEquals("thomas2", person.firstname)
		assertEquals("andersen", person.lastname)
	}

	@Test
	fun `get list of people`() {
		val responseEntity = restTemplate.getForEntity("/person", List::class.java)
		assertNotNull(responseEntity)
		assertEquals(200, responseEntity.statusCodeValue)
		val mapper = ObjectMapper()
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
		responseEntity.body.forEach(action = { it ->
			println(it)
		/*	val string = it.toString().replace("=", ":")
			println(string)
			val person = mapper.readValue(string, Person::class.java)
			assertNotNull(person)*/
		})

	}



	@Test
	fun `create person by http post`() {
		val name = "thomas2"
		val personUnderTest = Person(firstname = name, lastname = "andersen")

		/*val responseEntity = restTemplate.postForEntity("/person", personUnderTest, Person::class.java)
		assertNotNull(responseEntity)
		assertEquals(200, responseEntity.statusCodeValue)*/

		datastore.createQuery(Person::class.java).filter("firstname", name).asIterable()
				.forEach({
					println("${it.firstname} ${it.lastname} ")
				})

	}
}
