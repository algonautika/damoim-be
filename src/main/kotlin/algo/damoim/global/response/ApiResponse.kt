import algo.damoim.global.exception.ExceptionSpec

/**
 * API 의 일관된 응답을 주기 위한 클래스
 */
sealed class ApiResponse<T> {
    data class Success<T>(val data: T?) : ApiResponse<T>()
    data class SuccessList<T>(val pageSize: Int, val page: Int, val data: List<T>) : ApiResponse<T>()
    data class Error<T>(val message: String) : ApiResponse<T>()

    companion object {
        fun <T> success(data: T?) = Success(data)

        fun <T> successPage(
            pageSize: Int,
            page: Int,
            data: List<T>,
        ) = SuccessList(pageSize, page, data)

        fun <T> error(
            exception: ExceptionSpec
        ): Error<T> {
            return Error(exception.name)
        }
    }
}
