package eu.pitlap.shared.race.data.source

import eu.pitlap.shared.core.data.api.HttpClientProvider
import eu.pitlap.shared.core.data.api.safeCall
import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.race.data.dto.RaceSummaryDto
import eu.pitlap.shared.race.data.dto.TrackSummaryDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "https://pitlap.eu/summary"

internal class SummaryDataSourceImpl(
    private val client: HttpClient = HttpClientProvider.client
): SummaryDataSource {
    override suspend fun getRaceSummary(
        year: Int,
        round: Int
    ): Result<RaceSummaryDto, ApiError.Remote> {
        return safeCall<RaceSummaryDto> {
            client.get(urlString = "$BASE_URL/race/$year/$round")
        }
    }

    override suspend fun getTrackSummary(trackName: String): Result<TrackSummaryDto, ApiError.Remote> {
        return safeCall<TrackSummaryDto> {
            client.get(urlString = "$BASE_URL/track/$trackName")
        }
    }
}
