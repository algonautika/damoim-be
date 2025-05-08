package algo.damoim.global.exception

import ApiResponse
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * REST 컨트롤러에서 발생하는 예외를 전역적으로 처리하는 핸들러 클래스
 *
 * `@RestControllerAdvice`를 통해 스프링 컨텍스트 내 모든 컨트롤러에서 발생하는
 * 예외를 이 핸들러로 모아, 일관된 에러 응답을 반환
 */
@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    companion object {
        private val log = LogManager.getLogger(GlobalExceptionHandler::class.java)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Nothing>> {
        printErrorMessage(e)
        val exception = GlobalException.UNCAUGHT_EXCEPTION

        val response = ApiResponse.error<Nothing>(
            exception = exception,
        )
        return ResponseEntity
            .status(exception.status)
            .body(response)
    }

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): ResponseEntity<ApiResponse<Nothing>> {
        printErrorMessage(e)
        val response = ApiResponse.error<Nothing>(
            exception = e.exception
        )
        return ResponseEntity
            .status(e.exception.status)
            .body(response)
    }

    // 400 Bad Request (JSON 형식이 잘못된 경우)
    override fun handleHttpMessageNotReadable(
        e: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest,
    ): ResponseEntity<Any> {
        printErrorMessage(e)
        val response = ApiResponse.error<Nothing>(
            exception = GlobalException.JSON_BAD_REQUEST,
        )

        return ResponseEntity
            .status(status)
            .body(response)
    }

    // 404 Not Found
    override fun handleNoHandlerFoundException(
        ex: NoHandlerFoundException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest,
    ): ResponseEntity<Any>? {
        val response = ApiResponse.error<Nothing>(
            exception = GlobalException.NOT_FOUND,
        )
        return ResponseEntity
            .status(status)
            .body(response)
    }

    /**
     * Exception 객체로부터 에러 메시지를 추출하는 메소드
     */
    private fun printErrorMessage(e: Exception) {
        when (e) {
            is NullPointerException -> log.error(e.stackTraceToString())
            is ExceptionSpec -> {} // do nothing
            else -> log.error(e.stackTraceToString())
        }
    }
}
