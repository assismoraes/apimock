package br.assismoraes.apimock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApimockApplication

fun main(args: Array<String>) {
	runApplication<ApimockApplication>(*args)
}
