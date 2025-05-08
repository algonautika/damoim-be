package algo.damoim.global.util

import ApiResponse
import algo.damoim.global.exception.ExceptionSpec
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import java.nio.charset.StandardCharsets

class ExceptionUtil {
    companion object {
        fun writeErrorJson(
            response: HttpServletResponse,
            exception: ExceptionSpec
        ) {
            val errorResponse = ApiResponse.error<Nothing>(
                exception = exception
            )

            val json = ObjectMapper().writeValueAsString(errorResponse)

            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.characterEncoding = StandardCharsets.UTF_8.name()
            response.status = exception.status.value()

            response.writer.write(json)
            response.writer.flush()
            response.writer.close()
        }
    }
}
