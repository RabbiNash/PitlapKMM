package eu.pitlap.shared.standings.data.source

import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.standings.data.dto.ConstructorStandingDto
import eu.pitlap.shared.standings.data.dto.DriverStandingDto

internal interface StandingsDataSource {
    suspend fun getDriverStandings(): Result<List<DriverStandingDto>, ApiError.Remote>
    suspend fun getConstructorStandings(): Result<List<ConstructorStandingDto>, ApiError.Remote>
}
