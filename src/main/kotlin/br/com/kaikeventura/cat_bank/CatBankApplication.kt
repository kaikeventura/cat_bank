package br.com.kaikeventura.cat_bank

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CatBankApplication

fun main(args: Array<String>) {
	runApplication<CatBankApplication>(*args)
}
