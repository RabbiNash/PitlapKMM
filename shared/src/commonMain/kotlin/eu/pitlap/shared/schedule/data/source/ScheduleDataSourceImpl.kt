package eu.pitlap.shared.schedule.data.source

import eu.pitlap.shared.core.data.api.HttpClientProvider
import eu.pitlap.shared.core.data.api.safeCall
import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.schedule.data.dto.EventScheduleDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "https://pitlap.eu/schedule"

internal class ScheduleDataSourceImpl(
    private val client: HttpClient = HttpClientProvider.client
): ScheduleDataSource {
    override suspend fun getEventSchedule(year: Int): Result<List<EventScheduleDto>, ApiError.Remote> {
        return safeCall<List<EventScheduleDto>> {
            client.get(urlString = BASE_URL)
        }
    }
}
