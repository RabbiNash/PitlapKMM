package eu.pitlap.shared.standings.domain.repository

import eu.pitlap.shared.standings.domain.model.ConstructorStandingModel
import eu.pitlap.shared.standings.domain.model.DriverStandingModel

internal interface StandingsRepository {
    @Throws(Throwable::class)
    suspend fun getDriverStandings(): List<DriverStandingModel>

    @Throws(Throwable::class)
    suspend fun getConstructorStandings(): List<ConstructorStandingModel>
}
