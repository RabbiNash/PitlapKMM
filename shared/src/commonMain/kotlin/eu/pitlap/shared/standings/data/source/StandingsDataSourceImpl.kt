package eu.pitlap.shared.standings.data.source

import eu.pitlap.shared.core.data.api.HttpClientProvider
import eu.pitlap.shared.core.data.api.safeCall
import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.standings.data.dto.ConstructorStandingDto
import eu.pitlap.shared.standings.data.dto.DriverStandingDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "https://pitlap.eu/standings"

internal class StandingsDataSourceImpl(
    private val client: HttpClient = HttpClientProvider.client
): StandingsDataSource {
    override suspend fun getDriverStandings(): Result<List<DriverStandingDto>, ApiError.Remote> {
        return safeCall<List<DriverStandingDto>> {
            client.get(urlString = "$BASE_URL/driver")
        }
    }

    override suspend fun getConstructorStandings(): Result<List<ConstructorStandingDto>, ApiError.Remote> {
        return safeCall<List<ConstructorStandingDto>> {
            client.get(urlString = "$BASE_URL/constructor")
        }
    }
}
