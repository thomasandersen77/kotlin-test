package org.andtho.kotlin.web.restkotlin

import com.mongodb.MongoClient
import com.mongodb.annotations.ThreadSafe
import de.flapdoodle.embed.mongo.MongodProcess
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.IMongoCmdOptions
import de.flapdoodle.embed.mongo.config.MongoCmdOptionsBuilder
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder
import de.flapdoodle.embed.mongo.config.Net
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.process.runtime.Network
import org.junit.rules.ExternalResource

@ThreadSafe
object MongoServerResource : ExternalResource() {
    private val starter = MongodStarter.getDefaultInstance()
    private var _mongod: MongodProcess? = null
    private var _mongo: MongoClient? = null

    @Throws(Throwable::class)
    override fun before(){
        val port = 27017
        val _mongodExe = starter.prepare(MongodConfigBuilder()
                .version(Version.Main.DEVELOPMENT)
                .cmdOptions(MongoCmdOptionsBuilder().useNoPrealloc(true).verbose(false).useSmallFiles(true).build())
                .net(Net("localhost", port, Network.localhostIsIPv6()))
                .build())
        _mongod = _mongodExe.start()
        _mongo = MongoClient("localhost", port)
    }

    override fun after() {
       _mongod?.stopInternal()
    }
}