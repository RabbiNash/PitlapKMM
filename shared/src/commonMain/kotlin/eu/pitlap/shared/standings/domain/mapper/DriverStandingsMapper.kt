package eu.pitlap.shared.standings.domain.mapper

import eu.pitlap.shared.standings.data.dto.ConstructorStandingDto
import eu.pitlap.shared.standings.data.dto.DriverStandingDto
import eu.pitlap.shared.standings.domain.model.ConstructorStandingModel
import eu.pitlap.shared.standings.domain.model.DriverStandingModel

internal class DriverStandingsMapper {
    fun mapToDriverStandings(
        standings: List<DriverStandingDto>
    ): List<DriverStandingModel> {
        return standings.map {
            DriverStandingModel(
                position = it.position,
                positionText = it.positionText,
                points = it.points,
                wins = it.wins,
                driverId = it.driverID,
                driverNumber = it.driverNumber,
                givenName = it.givenName,
                familyName = it.familyName,
                constructorName = it.constructorName,
            )
        }
    }

    fun mapToConstructorStandings(
        standings: List<ConstructorStandingDto>
    ): List<ConstructorStandingModel> {
        return standings.map {
            ConstructorStandingModel(
                position = it.position,
                positionText = it.positionText,
                points = it.points,
                wins = it.wins,
                constructorName = it.constructorName,
                constructorId = it.constructorId,
            )
        }
    }
}
