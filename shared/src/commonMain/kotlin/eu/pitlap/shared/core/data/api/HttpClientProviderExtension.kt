package eu.pitlap.shared.core.data.api

import eu.pitlap.shared.core.data.models.ApiResponse
import eu.pitlap.shared.core.domain.ApiError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext
import eu.pitlap.shared.core.domain.Result

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, ApiError.Remote> {
    val response = try {
        execute()
    } catch(e: SocketTimeoutException) {
        return Result.Error(ApiError.Remote.REQUEST_TIMEOUT)
    } catch(e: UnresolvedAddressException) {
        return Result.Error(ApiError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(ApiError.Remote.UNKNOWN)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, ApiError.Remote> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                val apiResponse = response.body<ApiResponse<T>>()

                return if (apiResponse.success) {
                    Result.Success(apiResponse.data)
                } else {
                    Result.Error(ApiError.Remote.SERVER)
                }

            } catch (e: NoTransformationFoundException) {
                Result.Error(ApiError.Remote.SERIALIZATION)
            } catch (e: Exception) {
                coroutineContext.ensureActive()
                Result.Error(ApiError.Remote.SERIALIZATION)
            }
        }

        408 -> Result.Error(ApiError.Remote.REQUEST_TIMEOUT)
        429 -> Result.Error(ApiError.Remote.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(ApiError.Remote.SERVER)
        else -> Result.Error(ApiError.Remote.UNKNOWN)
    }
}
