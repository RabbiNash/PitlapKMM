package eu.pitlap.shared.core.data.api

import eu.pitlap.shared.core.data.models.ergast.ErgastResponse
import eu.pitlap.shared.core.data.models.ergast.tables.ErgastTable
import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T : ErgastTable> ergast(
    noinline call: suspend () -> HttpResponse
) = safeErgastCall<T>(call)

suspend inline fun <reified T : ErgastTable> safeErgastCall(
    crossinline execute: suspend () -> HttpResponse
): Result<T, ApiError.Remote> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        return Result.Error(ApiError.Remote.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        return Result.Error(ApiError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(ApiError.Remote.UNKNOWN)
    }

    return when (response.status.value) {
        in 200..299 -> {
            try {
                val ergastResponse: ErgastResponse = response.body()
                val table = ergastResponse.mrData.table

                if (table is T) {
                    Result.Success(table)
                } else {
                    Result.Error(ApiError.Remote.SERIALIZATION)
                }
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
