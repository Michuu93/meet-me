package com.meetme.meetmeserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MeetmeServerApplication

fun main(args: Array<String>) {
	runApplication<MeetmeServerApplication>(*args)
}
