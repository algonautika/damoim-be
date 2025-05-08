package algo.damoim.global.exception

import org.springframework.http.HttpStatus

enum class GlobalException(
    override val status: HttpStatus
) : ExceptionSpec {
    UNCAUGHT_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
    FORBIDDEN(HttpStatus.FORBIDDEN),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    JSON_BAD_REQUEST(HttpStatus.BAD_REQUEST),
}
