package eu.pitlap.shared.cache.dao.standings

import eu.pitlap.shared.cache.ConstructorStandingMeta
import eu.pitlap.shared.cache.DriverStandingMeta
import eu.pitlap.shared.standings.domain.model.ConstructorStandingModel
import eu.pitlap.shared.standings.domain.model.DriverStandingModel

internal interface StandingDAO {
    fun getDriverStandings(): List<DriverStandingModel>
    fun getConstructorStandings(): List<ConstructorStandingModel>
    fun clearAndCreateDriverStandings(events: List<DriverStandingModel>)
    fun clearAndCreateConstructorStandings(events: List<ConstructorStandingModel>)
    fun getDriverStandingsMeta(): DriverStandingMeta?
    fun getConstructorStandingsMeta(): ConstructorStandingMeta?
    fun insertDriverStandingMeta(timestamp: String)
    fun insertConstructorStandingMeta(timestamp: String)
}
