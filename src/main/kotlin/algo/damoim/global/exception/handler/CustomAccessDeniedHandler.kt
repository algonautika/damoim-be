package algo.damoim.global.exception.handler

import algo.damoim.global.exception.GlobalException
import algo.damoim.global.util.ExceptionUtil
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.logging.log4j.LogManager
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class CustomAccessDeniedHandler : AccessDeniedHandler {
    companion object {
        private val log = LogManager.getLogger(CustomAccessDeniedHandler::class.java)
    }

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException,
    ) {
        log.warn("Forbidden 403 error: {}", accessDeniedException.message)

        ExceptionUtil.writeErrorJson(
            response,
            GlobalException.FORBIDDEN,
        )
    }
}
