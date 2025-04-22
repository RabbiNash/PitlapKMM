package eu.pitlap.shared.standings.domain.repository

import eu.pitlap.shared.standings.domain.model.ConstructorStandingModel
import eu.pitlap.shared.standings.domain.model.DriverStandingModel

internal interface StandingsRepository {
    @Throws(Throwable::class)
    suspend fun getDriverStandings(forceRefresh: Boolean = false): List<DriverStandingModel>

    @Throws(Throwable::class)
    suspend fun getConstructorStandings(forceRefresh: Boolean): List<ConstructorStandingModel>
}
