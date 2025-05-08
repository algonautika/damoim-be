package algo.damoim.global.exception

import algo.damoim.global.util.ExceptionUtil
import jakarta.servlet.http.HttpServletResponse
import org.apache.logging.log4j.LogManager

/**
 * Global Exception Handler 에서 처리되지 않은 예외를 처리하는 클래스
 */
object FailureHandler {
    private val log = LogManager.getLogger(FailureHandler::class.java)

    fun handleFailure(
        throwable: Throwable,
        response: HttpServletResponse,
    ) {
        when (throwable) {
            is ExceptionSpec -> {
                ExceptionUtil.writeErrorJson(
                    response = response,
                    exception = throwable
                )
                throw throwable
            }

            else -> {
                log.error(throwable.stackTraceToString())
                ExceptionUtil.writeErrorJson(
                    response,
                    GlobalException.UNCAUGHT_EXCEPTION
                )
                throw throwable
            }
        }
    }
}
