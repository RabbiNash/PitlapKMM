package eu.pitlap.shared.core.domain

sealed interface ApiError: Error {
    enum class Remote: ApiError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN
    }

    enum class Local: ApiError {
        DISK_FULL,
        UNKNOWN
    }
}

fun ApiError.Remote.toThrowable(): Throwable {
    val message = when (this) {
        ApiError.Remote.NO_INTERNET -> "No internet connection"
        ApiError.Remote.REQUEST_TIMEOUT -> "Request timed out"
        ApiError.Remote.SERVER -> "Server error"
        ApiError.Remote.SERIALIZATION -> "Serialization failed"
        ApiError.Remote.TOO_MANY_REQUESTS -> "Too many requests"
        ApiError.Remote.UNKNOWN -> "Unknown error"
    }

    return Exception(message)
}
