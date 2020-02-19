package com.example.hazelcastdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HazelcastdemoApplication

fun main(args: Array<String>) {
	runApplication<HazelcastdemoApplication>(*args)
}
