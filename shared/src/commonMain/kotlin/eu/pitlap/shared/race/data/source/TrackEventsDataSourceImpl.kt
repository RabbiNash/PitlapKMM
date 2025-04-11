package eu.pitlap.shared.race.data.source

import eu.pitlap.shared.core.data.api.HttpClientProvider
import eu.pitlap.shared.core.data.api.safeCall
import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.race.data.dto.PracticeLapsDto
import eu.pitlap.shared.race.data.dto.QualifyingResultsDto
import eu.pitlap.shared.race.data.dto.RaceResultsDto
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
    ): Result<QualifyingResultsDto, ApiError.Remote> {
        return safeCall<QualifyingResultsDto> {
            client.get(urlString = "$BASE_URL/quali/convectional/$year/$round")
        }
    }

    override suspend fun getRaceResults(year: Int, round: Int): Result<RaceResultsDto, ApiError.Remote> {
        return safeCall<RaceResultsDto> {
            client.get(urlString = "$BASE_URL/race/result/convectional/$year/$round")
        }
    }
}
