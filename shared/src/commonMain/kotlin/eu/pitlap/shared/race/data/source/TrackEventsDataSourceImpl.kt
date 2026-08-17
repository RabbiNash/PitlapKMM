package eu.pitlap.shared.race.data.source

import eu.pitlap.shared.core.data.api.HttpClientProvider
import eu.pitlap.shared.core.data.api.ergast
import eu.pitlap.shared.core.data.api.safeCall
import eu.pitlap.shared.core.data.models.ergast.tables.RaceTable
import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.race.data.dto.PracticeLapsDto
import eu.pitlap.shared.race.data.dto.TopSpeedsDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "https://pitlap.eu/"

internal class TrackEventsDataSourceImpl(
    private val client: HttpClient = HttpClientProvider.client
): TrackEventsDataSource {
    override suspend fun getPracticeLaps(
        year: Int,
        round: Int,
        sessionName: String
    ): Result<PracticeLapsDto, ApiError.Remote> {
        return safeCall<PracticeLapsDto> {
            client.get(urlString = "$BASE_URL/practice/$year/$round/$sessionName")
        }
    }

    override suspend fun getQualifyingResults(
        year: Int,
        round: Int
    ): Result<RaceTable, ApiError.Remote> {
        return ergast<RaceTable> {
            client.get(urlString = "https://api.jolpi.ca/ergast/f1/$year/$round/qualifying")
        }
    }

    override suspend fun getRaceResults(year: Int, round: Int): Result<RaceTable, ApiError.Remote> {
        return ergast<RaceTable> {
            client.get(urlString = "https://api.jolpi.ca/ergast/f1/$year/$round/results")
        }
    }

    override suspend fun getTopSpeeds(
        year: Int,
        round: Int,
        sessionName: String
    ): Result<TopSpeedsDto, ApiError.Remote> {
        return safeCall<TopSpeedsDto> {
            client.get(urlString = "$BASE_URL/speed/$year/$round/$sessionName")
        }
    }
}
