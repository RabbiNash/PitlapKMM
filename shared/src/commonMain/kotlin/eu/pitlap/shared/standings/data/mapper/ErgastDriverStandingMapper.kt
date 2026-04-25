package eu.pitlap.shared.standings.data.mapper

import eu.pitlap.shared.standings.data.dto.ConstructorStandingDto
import eu.pitlap.shared.standings.data.dto.DriverStandingDto
import eu.pitlap.shared.core.data.models.ergast.tables.StandingsTable

internal fun StandingsTable.toDriverStandings(): List<DriverStandingDto> {
    return standingsLists
        .firstOrNull()
        ?.driverStandings
        ?.map { standing ->
            DriverStandingDto(
                position = standing.position.toInt(),
                positionText = standing.positionText,
                points = standing.points.toInt(),
                wins = standing.wins.toInt(),
                driverID = standing.driver.driverId,
                driverNumber = standing.driver.permanentNumber?.toInt() ?: 0,
                givenName = standing.driver.givenName,
                familyName = standing.driver.familyName,
                constructorName = standing.constructors.firstOrNull()?.name.orEmpty()
            )
        }
        ?: emptyList()
}

internal fun StandingsTable.toConstructorStandings(): List<ConstructorStandingDto> {
    return standingsLists
        .firstOrNull()
        ?.constructorStandings
        ?.map { standing ->
            ConstructorStandingDto(
                position = standing.position.toInt(),
                positionText = standing.positionText,
                points = standing.points.toInt(),
                wins = standing.wins.toInt(),
                constructorId = standing.constructor.constructorId,
                constructorName = standing.constructor.name
            )
        }
        ?: emptyList()
}
