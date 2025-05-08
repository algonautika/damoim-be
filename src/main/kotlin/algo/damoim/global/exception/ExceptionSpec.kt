package algo.damoim.global.exception

import org.springframework.http.HttpStatus

interface ExceptionSpec {
    val status: HttpStatus
    val name: String
}
