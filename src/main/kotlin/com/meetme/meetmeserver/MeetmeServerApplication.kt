package com.meetme.meetmeserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.reactive.config.EnableWebFlux


@EnableWebFlux
@SpringBootApplication
@EnableR2dbcRepositories
@EnableTransactionManagement
class MeetmeServerApplication

fun main(args: Array<String>) {
    runApplication<MeetmeServerApplication>(*args)
}
