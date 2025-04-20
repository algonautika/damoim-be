package algo.damoim

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DamoimApplication

fun main(args: Array<String>) {
    println("=== ENV DUMP BEFORE SPRING START ===")
    println("DB_URL      = ${System.getenv("DB_URL")}")
    println("DB_USERNAME = ${System.getenv("DB_USERNAME")}")
     println("DB_PASSWORD = ${System.getenv("DB_PASSWORD")}")
    println("====================================")
    runApplication<DamoimApplication>(*args)
}
