package eu.pitlap.shared.cache.dao.standings

import eu.pitlap.shared.cache.ConstructorStandingMeta
import eu.pitlap.shared.cache.ConstructorStandingMetaQueries
import eu.pitlap.shared.cache.ConstructorStandingsQueries
import eu.pitlap.shared.cache.DriverStandingMeta
import eu.pitlap.shared.cache.DriverStandingMetaQueries
import eu.pitlap.shared.cache.DriverStandingsQueries
import eu.pitlap.shared.cache.mapper.StandingMapper
import eu.pitlap.shared.standings.domain.model.ConstructorStandingModel
import eu.pitlap.shared.standings.domain.model.DriverStandingModel

internal class StandingDAOImpl(
    private val driverStandingsQueries: DriverStandingsQueries,
    private val constructorStandingsQueries: ConstructorStandingsQueries,
    private val driverStandingMetaQueries: DriverStandingMetaQueries,
    private val constructorStandingMetaQueries: ConstructorStandingMetaQueries
): StandingDAO {

    override fun getDriverStandings(): List<DriverStandingModel> {
       return driverStandingsQueries.selectAll(StandingMapper::mapToDriverStandingModel).executeAsList()
    }

    override fun getConstructorStandings(): List<ConstructorStandingModel> {
        return constructorStandingsQueries.selectAllConstructors(StandingMapper::mapToConstructorStandingModel).executeAsList()
    }

    override fun clearAndCreateDriverStandings(events: List<DriverStandingModel>) {
        events.forEach {
            driverStandingsQueries.insertOrReplaceDriverStanding(
                position = it.position.toLong(),
                positionText = it.positionText,
                points = it.points.toLong(),
                wins = it.wins.toLong(),
                driverID = it.driverId,
                driverNumber = it.driverNumber.toLong(),
                givenName = it.givenName,
                familyName = it.familyName,
                constructorName = it.constructorName
            )
        }
    }

    override fun clearAndCreateConstructorStandings(events: List<ConstructorStandingModel>) {
        events.forEach {
            constructorStandingsQueries.insertOrReplaceConstructorStanding(
                position = it.position.toLong(),
                positionText = it.positionText,
                points = it.points.toLong(),
                wins = it.wins.toLong(),
                constructorId = it.constructorId,
                constructorName = it.constructorName
            )
        }
    }

    override fun getDriverStandingsMeta(): DriverStandingMeta? {
        return driverStandingMetaQueries.getMeta().executeAsOneOrNull()
    }

    override fun getConstructorStandingsMeta(): ConstructorStandingMeta? {
        return constructorStandingMetaQueries.getMeta().executeAsOneOrNull()
    }

    override fun insertDriverStandingMeta(timestamp: String) {
        driverStandingMetaQueries.insertMeta(timestamp)
    }

    override fun insertConstructorStandingMeta(timestamp: String) {
        constructorStandingMetaQueries.insertMeta(timestamp)
    }
}
