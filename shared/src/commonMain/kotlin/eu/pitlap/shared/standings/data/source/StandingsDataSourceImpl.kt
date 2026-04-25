package eu.pitlap.shared.standings.data.source

import eu.pitlap.shared.core.data.api.HttpClientProvider
import eu.pitlap.shared.core.data.api.ergast
import eu.pitlap.shared.core.data.models.ergast.tables.StandingsTable
import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.standings.data.dto.ConstructorStandingDto
import eu.pitlap.shared.standings.data.dto.DriverStandingDto
import eu.pitlap.shared.standings.data.mapper.toConstructorStandings
import eu.pitlap.shared.standings.data.mapper.toDriverStandings
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

internal class StandingsDataSourceImpl(
    private val client: HttpClient = HttpClientProvider.client
) : StandingsDataSource {

    @OptIn(ExperimentalTime::class)
    private val currentYear: Int
        get() {
            val now = Clock.System.now()
            val localDateTime = now.toLocalDateTime(TimeZone.UTC)
            return localDateTime.year
        }


    override suspend fun getDriverStandings(): Result<List<DriverStandingDto>, ApiError.Remote> {
        return fetchStandings(
            url = "https://api.jolpi.ca/ergast/f1/$currentYear/driverstandings"
        ) { it.toDriverStandings() }
    }

    override suspend fun getConstructorStandings(): Result<List<ConstructorStandingDto>, ApiError.Remote> {
        return fetchStandings(
            url = "https://api.jolpi.ca/ergast/f1/$currentYear/constructorstandings"
        ) { it.toConstructorStandings() }
    }

    private suspend inline fun <T> fetchStandings(
        url: String,
        crossinline mapper: (StandingsTable) -> List<T>
    ): Result<List<T>, ApiError.Remote> {
        return when (val result = ergast<StandingsTable> {
            client.get(url)
        }) {
            is Result.Success -> Result.Success(mapper(result.data))
            is Result.Error -> Result.Error(result.error)
        }
    }
}