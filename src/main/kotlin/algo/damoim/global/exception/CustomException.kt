package algo.damoim.global.exception

data class CustomException(
    val exception: ExceptionSpec
) : RuntimeException()
