package eu.pitlap.shared.radio.data.source

import eu.pitlap.shared.core.data.api.HttpClientProvider
import eu.pitlap.shared.core.data.api.safeCall
import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.radio.data.dto.TeamRadioDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "https://api.openf1.org/v1/team_radio"

internal class TeamRadioDataSourceImpl(
    private val client: HttpClient = HttpClientProvider.client
): TeamRadioDataSource {
    override suspend fun getLatestTeamRadio(driverNumber: Int): Result<List<TeamRadioDto>, ApiError.Remote> {
        return safeCall<List<TeamRadioDto>>(ignoreApiResponse = true) {
            client.get(urlString = "$BASE_URL?session_key=latest&driver_number=$driverNumber")
        }
    }
}
