package eu.pitlap.shared.schedule.data.source

import eu.pitlap.shared.core.data.api.HttpClientProvider
import eu.pitlap.shared.core.data.api.ergast
import eu.pitlap.shared.core.data.models.ergast.tables.RaceTable
import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.schedule.data.dto.EventScheduleDto
import eu.pitlap.shared.schedule.data.mapper.toEventSchedule
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class ScheduleDataSourceImpl(
    private val client: HttpClient = HttpClientProvider.client
): ScheduleDataSource {
    override suspend fun getEventSchedule(year: Int): Result<List<EventScheduleDto>, ApiError.Remote> {
        val url = "https://api.jolpi.ca/ergast/f1/$year/races/"

        return when (val result = ergast<RaceTable> {
            client.get(url)
        }) {
            is Result.Success -> {
                Result.Success(result.data.races.toEventSchedule())
            }
            is Result.Error -> Result.Error(result.error)
        }
    }
}
