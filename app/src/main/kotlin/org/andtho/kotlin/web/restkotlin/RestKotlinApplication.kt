package org.andtho.kotlin.web.restkotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@EnableAutoConfiguration
class RestKotlinApplication


fun main(args: Array<String>) {
    SpringApplication.run(RestKotlinApplication::class.java, *args)
}
