package algo.damoim

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
    exclude = [
        SecurityAutoConfiguration::class,
        SecurityFilterAutoConfiguration::class, // Servlet 기반 Security Filter 자동 설정을 명시적으로 제외
    ]
)
class DamoimApplication

fun main(args: Array<String>) {
    runApplication<DamoimApplication>(*args)

}
